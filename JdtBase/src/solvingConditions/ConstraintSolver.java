package solvingConditions;

import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConstraintSolver {
    private List<Expression> conditions;
    private List parameters;
    private String currentPath;
    public ConstraintSolver(List<Expression> conditions, List parameters, String currentPath){
        this.conditions = conditions;
        this.parameters = parameters;
        this.currentPath = currentPath;
    }

    public List<Variable> solveConditions(){
        if (conditions.size() != 0) {
            List<String> SMTCalls = new ArrayList<>();
            for (int j = 0; j < conditions.size(); j++) {
                //Get the condition of the testpath
                Expression condition = conditions.get(j);
//							System.out.println("Condition: " + condition.getClass());
                if (condition instanceof Assignment) {
                    //Get the left operand of the condition
                    Expression e1 = ((Assignment) condition).getLeftHandSide();
//								((InfixExpression) e1).setOperator(changeOperator(((InfixExpression) e1).getOperator()));
                    //Get the right operand of the condition
                    Expression e2 = ((Assignment) condition).getRightHandSide();

                    if (e1 instanceof InfixExpression) {
                        if (e2 instanceof BooleanLiteral) {
                            if (((BooleanLiteral) e2).booleanValue() == false) {
                                ((InfixExpression) e1).setOperator(changeOperator(((InfixExpression) e1).getOperator()));
                                ((BooleanLiteral) e2).setBooleanValue(true);
                            }
                        }
                    }
//								System.out.println("Left operand: " + ((Assignment) condition).getLeftHandSide().toString());
//								System.out.println("Right operand: " + e2 + "\n");
                    String SMTCall = giaiDieuKienRangBuoc(e1.toString());
                    SMTCalls.add(SMTCall);
                }
            }

            String SMTStart = createSMTStart(parameters);
            String SMTEnd = "(check-sat)\n(get-model)\n(exit)";

            String SMT = SMTStart + String.join("\n", SMTCalls) + "\n" + SMTEnd;
            File file = new File(currentPath + "\\ISM\\JdtBase\\src\\SMT\\SMT.smt2");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeToFile(SMT, file);
            Z3Solving z3 = new Z3Solving();
            String result = "";
            try {
                result = z3.solve(currentPath + "\\ISM\\JdtBase\\src\\SMT\\SMT.smt2");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return parseResult(result);
        } else {
            return null;
        }
    }

    public InfixExpression.Operator changeOperator(InfixExpression.Operator operator) {
        if (operator.equals(InfixExpression.Operator.LESS)) {
            return InfixExpression.Operator.GREATER_EQUALS;
        }
        if (operator.equals(InfixExpression.Operator.LESS_EQUALS)) {
            return InfixExpression.Operator.GREATER;
        }
        if (operator.equals(InfixExpression.Operator.GREATER)) {
            return InfixExpression.Operator.LESS_EQUALS;
        }
        if (operator.equals(InfixExpression.Operator.GREATER_EQUALS)) {
            return InfixExpression.Operator.LESS;
        }
        if (operator.equals(InfixExpression.Operator.EQUALS)) {
            return InfixExpression.Operator.NOT_EQUALS;
        }
        if (operator.equals(InfixExpression.Operator.NOT_EQUALS)) {
            return InfixExpression.Operator.EQUALS;
        }
        return operator;
    }

    public String giaiDieuKienRangBuoc(String s) {
        List<String> infix = chuyenBieuThucThanhInfix(s);
        Stack<String> stack = chuyenVeHauTo(infix);
        Nodes root = chuyenVeCayBieuThuc(stack);
        String tienTo = chuyenVeTienTo(root);
        String SMTCall = "(assert " + tienTo + ")";
        return SMTCall;
    }

    public List<String> chuyenBieuThucThanhInfix(String s) {
        //Example: s = "A*3>6*((D-4)+5)/2";
        List<String> stack = new ArrayList<>();
        Boolean isSoSanh = false;
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (c == '(') {
                if (temp != "") stack.add(temp);
                stack.add("(");
                temp = "";
            } else if (c == ')') {
                if (temp != "") stack.add(temp);
                stack.add(")");
                temp = "";
            } else if (laToanTu(c)) {
                if (laToanTuSoSanh(c)) {
                    if (!isSoSanh) {
                        if (temp != "") stack.add(temp);
                        temp = c + "";
                        isSoSanh = true;
                    } else {
                        temp += c;
                        stack.add(temp);
                        isSoSanh = false;
                        temp = "";
                    }
                }
                else {
                    if (temp != "") stack.add(temp);
                    stack.add(c + "");
                    temp = "";
                }
            } else {
                if (isSoSanh) {
                    stack.add(temp);
                    temp = c + "";
                    isSoSanh = false;
                } else {
                    temp += c;
                }
            }
        }
        if (temp != "") stack.add(temp);
        return stack;
    }

    public Stack<String> chuyenVeHauTo(List<String> s) {
        Stack<String> stack = new Stack<>();
        Stack<String> result = new Stack<>();
        for (int i = 0; i < s.size(); i++) {
            String c = s.get(i);
            if (c.equals(" ")) continue;
            if (c.equals("(")) {
                stack.push(c);
            } else if (c.equals(")")) {
                while (!stack.isEmpty() && stack.peek() != "(") {
                    result.push(stack.pop());
                }
                stack.pop();
            }
            else if (laToanTuSoSanh(c)) {
                while (!stack.isEmpty() && laToanTu(stack.peek())) {
                    result.push(stack.pop());
                }
                stack.push(c);
            }
            else if (laToanTu(c)) {
                while (!stack.isEmpty() && laToanTu(stack.peek()) && doUuTien(c) <= doUuTien(stack.peek())) {
                    result.push(stack.pop());
                }
                stack.push(c);
            }
            else {
                result.push(c);
            }
            //Print stack;
            for (int j = 0; j < stack.size(); j++) {
//				System.out.print(stack.get(j));
            }
//			System.out.print(" | ");
            for (int j = 0; j < result.size(); j++) {
//				System.out.print(result.get(j));
            }
//			System.out.println();
        }
        while (!stack.isEmpty()) {
            result.push(stack.pop());
        }
        return result;
    }

    public Nodes chuyenVeCayBieuThuc(Stack<String> s) {
        Stack<Nodes> stack = new Stack<>();
        for (int i = 0; i < s.size(); i++) {
            String c = s.get(i);
            Nodes node = new Nodes(c);
            if (!laToanTu(c)) {
                stack.push(node);
            } else {
                node.setRight(stack.pop());
                node.setLeft(stack.pop());
                stack.push(node);
            }
//			System.out.println(stack.peek().toString() + " ----- " + stack.size());

        }
        return stack.pop();
    }

    public boolean isIntegerNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean isIntegerVar(String s, List parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            SingleVariableDeclaration parameter = (SingleVariableDeclaration) parameters.get(i);
            if (checkVar(s, parameter.getName().toString()) && parameter.getType().toString().equals("int")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkVar(String s, String parameterName) {
        String[] parts = s.trim().split(" ");
        for (String part : parts) {
            if (part.equals(parameterName)) {
                return true;
            }
        }
        return false;
    }

    public String chuyenVeTienTo(Nodes root) {
        String Retstr = "", left = "", right = "";
        String c = root.getData();
        if (laToanTu(c)) {
            left = chuyenVeTienTo(root.getLeft());
            right = chuyenVeTienTo(root.getRight());
        }
        if (c.equals("!=")) {
            Retstr = "(or ( > " + left + " " + right + " ) ( < " + left + " " + right + " )) ";
        }
        else if (laToanTu(c)) {
            if (c.equals("%")) c = "mod";
            else if (c.equals("/")) {
                if (isIntegerVar(left, parameters) && isIntegerNumber(right)) {
                    c = "div";
                }
                else {
                    c = "/";
                }
            }
            Retstr = "( " + c + " " + left + " " + right + " )";
        }
        else {
            Retstr = c + "";
        }
        Retstr = Retstr.replace("==", "=");
//		System.out.println(Retstr);
        return Retstr;
    }

    public void writeToFile(String data, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String convert(String type) {
        if (type.equals("int") || type.equals("short") || type.equals("long")) {
            return "Int";
        }
        if (type.equals("boolean")) {
            return "Bool";
        }
        if (type.equals("double") || type.equals("float")) {
            return "Real";
        }
        return type;
    }


    public List<Variable> parseResult(String result) {
        List<Variable> variables = new ArrayList<>();
        boolean inCurrentVariable = false;
        Variable variable = null;
        // Parse result
        for (String line : result.split("\n")) {
            if (line.contains("define-fun")) {
                // Get variable name and type
                // Example: (define-fun A () Int
                String[] parts = line.trim().split(" ");
                String name = parts[1].trim();
                String type = parts[3].trim();
                inCurrentVariable = true;
                variable = new Variable(name, type);
            } else if (inCurrentVariable) {
                // Get variable value
                // Example: 3)
                String value = line.trim();
                value = value.substring(0, value.length() - 1);
                if(value.contains("(")){
                    value = value.substring(1, value.length() - 1);
                }
                if(value.contains(" ")){
                    String[] parts = value.trim().split(" ");
                    value = parts[0] + parts[1];
                }
                variable.setValue(value);
                variables.add(variable);
                inCurrentVariable = false;
            }
        }
        return variables;
    }

    public String createSMTStart(List parameters) {
        String SMTStart = "";
        for (int i = 0; i < parameters.size(); i++) {
            SingleVariableDeclaration parameter = (SingleVariableDeclaration) parameters.get(i);
            String type = parameter.getType().toString();
            String name = parameter.getName().toString();
            SMTStart += "(declare-fun " + name + " () " + convert(type) + ")\n";
        }
        return SMTStart;
    }

    public int doUuTien(String c) {
        if (c.equals("+") || c.equals("-")) return 1;
        if (c.equals("*") || c.equals("/") || c.equals("%")) return 2;
        return 0;
    }

    public boolean laToanTu(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("%") || c.equals("!") || c.equals(">") || c.equals("<") || c.equals("==") || c.equals("<=") || c.equals(">=") || c.equals("!=");

    }

    public boolean laToanTu(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '!' || c == '>' || c == '<' || c == '=' || c == '<' || c == '>' || c == '!';
    }

    public boolean laToanTuSoSanh(String c) {
        return c.equals(">") || c.equals("<") || c.equals("==") || c.equals("<=") || c.equals(">=") || c.equals("!=");
    }

    public boolean laToanTuSoSanh(char c) {
        return c == '>' || c == '<' || c == '=' || c == '<' || c == '>' || c == '!';
    }

}
