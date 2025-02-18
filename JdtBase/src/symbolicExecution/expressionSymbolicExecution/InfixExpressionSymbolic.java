package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import symbolicExecution.Variable;

import java.util.ArrayList;
import java.util.List;

public class InfixExpressionSymbolic extends ExpressionSymbolic{
    private InfixExpression infixExpression;
    private Expression leftOperator;
    private Operator operator;
    private Expression rightOperator;
    private List<Expression> extendedOperands;
    public InfixExpressionSymbolic(InfixExpression infixExpression, List<Variable> variables){
        super(infixExpression, variables);
        this.infixExpression = infixExpression;
        this.operator = infixExpression.getOperator();// bao gồm =, +=, -=, *=, /=
        this.leftOperator = infixExpression.getLeftOperand();
        this.rightOperator = infixExpression.getRightOperand();
        extendedOperands = new ArrayList<>();
        for(Object object : infixExpression.extendedOperands()){
            extendedOperands.add((Expression) object);
        }
    }

    public InfixExpressionSymbolic(List<Variable> variables){
        super(null, variables);
        this.operator = null;
        this.leftOperator = null;
        this.rightOperator = null;

    }

    @Override
    public Expression symbolicExecutionExpression() {

        if(leftOperator instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) leftOperator, variables);
            leftOperator = arrayAccessSymbolic.symbolicExecutionExpression();
            setType(arrayAccessSymbolic.getType());
        } else if (leftOperator instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) leftOperator, variables);
            leftOperator = infixExpressionSymbolic.symbolicExecutionExpression();
            setType(infixExpressionSymbolic.getType());
        } else if (leftOperator instanceof ParenthesizedExpression) {
            ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) leftOperator, variables);
            leftOperator = parenthesizedExpressionSymbolic.symbolicExecutionExpression();
        } else if(leftOperator instanceof  PostfixExpression){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) leftOperator, variables);
            postfixExpressionSymbolic.symbolicExecutionExpression();
        } else if (leftOperator instanceof Name) {
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) leftOperator, variables);
            leftOperator = variableSymbolic.symbolicExecutionExpression();
            setType(variableSymbolic.getType());
        }

        if(rightOperator instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) rightOperator, variables);
            rightOperator = arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (rightOperator instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) rightOperator, variables);
            rightOperator = infixExpressionSymbolic.symbolicExecutionExpression();
        } else if (rightOperator instanceof ParenthesizedExpression) {
            ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) rightOperator, variables);
            rightOperator = parenthesizedExpressionSymbolic.symbolicExecutionExpression();
        } else if(rightOperator instanceof  PostfixExpression){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) rightOperator, variables);
            postfixExpressionSymbolic.symbolicExecutionExpression();
        } else if (rightOperator instanceof Name) {
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) rightOperator, variables);
            rightOperator = variableSymbolic.symbolicExecutionExpression();
            setType(variableSymbolic.getType());
        }

        for(int i = 0; i < extendedOperands.size(); i++) {
            if (extendedOperands.get(i) instanceof ArrayAccess) {
                ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) extendedOperands.get(i), variables);
                extendedOperands.set(i, arrayAccessSymbolic.symbolicExecutionExpression());
            } else if (extendedOperands.get(i) instanceof InfixExpression) {
                InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) extendedOperands.get(i), variables);
                extendedOperands.set(i, infixExpressionSymbolic.symbolicExecutionExpression());
            } else if (extendedOperands.get(i) instanceof ParenthesizedExpression) {
                ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) extendedOperands.get(i), variables);
                extendedOperands.set(i, parenthesizedExpressionSymbolic.symbolicExecutionExpression());
            } else if(extendedOperands.get(i) instanceof  PostfixExpression){
                PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) extendedOperands.get(i), variables);
                extendedOperands.set(i, postfixExpressionSymbolic.symbolicExecutionExpression());
            } else if (extendedOperands.get(i) instanceof Name) {
                VariableSymbolic variableSymbolic = new VariableSymbolic((Name) extendedOperands.get(i), variables);
                extendedOperands.set(i, variableSymbolic.symbolicExecutionExpression());
                setType(variableSymbolic.getType());
            }

        }
        return calculate();
    }

    public Expression calculate(){
        double resultNumber = 0;
        String resultString = "";
        List<Expression> variableExpressions = new ArrayList<>();

        // cấn cấn
        if(leftOperator instanceof Name){
            variableExpressions.add(leftOperator);
        } else if(leftOperator instanceof NumberLiteral){
            resultNumber = Double.parseDouble(((NumberLiteral) leftOperator).getToken());
        } else if (leftOperator instanceof StringLiteral){
            resultString = leftOperator.toString();
        } else {
            variableExpressions.add(leftOperator);
        }

        if (operator.equals(Operator.PLUS)) {
            if(rightOperator instanceof Name){
                variableExpressions.add(rightOperator);
            } else if (rightOperator instanceof NumberLiteral) {
                resultNumber += Double.parseDouble(((NumberLiteral) rightOperator).getToken());
            } else if (rightOperator instanceof StringLiteral) {
                resultString += rightOperator.toString();
            }  else {
                variableExpressions.add(rightOperator);
            }
            for (Object expression1 : extendedOperands) {
                if (expression1 instanceof NumberLiteral) {
                    resultNumber += Double.parseDouble(((NumberLiteral) expression1).getToken());
                } else if (expression1 instanceof StringLiteral) {
                    resultString += expression1.toString();
                } else {
                    variableExpressions.add((Expression) expression1);
                }
            }
        } else if (operator.equals(Operator.MINUS)) {
            if (rightOperator instanceof NumberLiteral) {
                resultNumber -= Double.parseDouble(((NumberLiteral) rightOperator).getToken());
            } else {
                variableExpressions.add(rightOperator);
            }
            for (Object expression1 : extendedOperands) {
                if (expression1 instanceof NumberLiteral) {
                    resultNumber -= Double.parseDouble(((NumberLiteral) expression1).getToken());
                } else {
                    variableExpressions.add((Expression) expression1);
                }
            }
        } else if (operator.equals(Operator.TIMES)) {
            if (!variableExpressions.isEmpty()) {
                resultNumber = 1;
            }
            if (rightOperator instanceof NumberLiteral) {
                resultNumber *= Double.parseDouble(((NumberLiteral) rightOperator).getToken());
            } else {
                variableExpressions.add(rightOperator);
            }
            for (Object expression1 : extendedOperands) {
                if (expression1 instanceof NumberLiteral) {
                    resultNumber *= Double.parseDouble(((NumberLiteral) expression1).getToken());
                } else {
                    variableExpressions.add((Expression) expression1);
                }
            }
        } else if (operator.equals(Operator.DIVIDE)) {
            if (!variableExpressions.isEmpty()) {
                resultNumber = 1;
            }
            if (rightOperator instanceof NumberLiteral) {
                resultNumber /= Double.parseDouble(((NumberLiteral) rightOperator).getToken());
            } else {
                variableExpressions.add(rightOperator);
            }
            for (Object expression1 : extendedOperands) {
                if (expression1 instanceof NumberLiteral) {
                    resultNumber /= Double.parseDouble(((NumberLiteral) expression1).getToken());
                } else {
                    variableExpressions.add((Expression) expression1);
                }
            }
        } else if(operator.equals(Operator.REMAINDER)){
            boolean checkVariable = false;
            if(!(leftOperator instanceof NumberLiteral) || !(rightOperator instanceof NumberLiteral)){
                checkVariable = true;
            }
            for (Object expression1 : extendedOperands){
                if(!(expression1 instanceof NumberLiteral)){
                    checkVariable = true;
                }
            }

            if(!checkVariable){
                int leftInt = Integer.parseInt(((NumberLiteral) leftOperator).getToken());
                int rightInt = Integer.parseInt(((NumberLiteral) rightOperator).getToken());
                int result = leftInt % rightInt;
                for (Object expression1 : extendedOperands){
                    int intNumber = Integer.parseInt(((NumberLiteral) expression1).getToken());
                    result = result % intNumber;
                }
                return NumberLiteralSymbolic.createNumberLiteral(Integer.toString(result));
            } else {
                InfixExpression iExpression  = createInfixExpression(leftOperator, rightOperator, operator);
                for (Object expression1 : extendedOperands){
                    iExpression  = createInfixExpression(iExpression,(Expression) expression1, operator);
                }
                return iExpression;
            }


        }else {
            throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        if (variableExpressions.isEmpty()){
            if(leftOperator instanceof NumberLiteral){
                return typeCasting(resultNumber);
            } else{
                return StringLiteralSymbolic.createStringLiteral(resultString);
            }
        } else {
            InfixExpression iExpression ;
            if (variableExpressions.get(0) != leftOperator){
                if(leftOperator instanceof NumberLiteral){
                    iExpression =  createInfixExpression(typeCasting(resultNumber), variableExpressions.get(0), operator);
                } else {
                    iExpression =  createInfixExpression(StringLiteralSymbolic.createStringLiteral(resultString), variableExpressions.get(0), operator);
                }
                for(int i = 1; i < variableExpressions.size(); i++){
                    iExpression = createInfixExpression(iExpression, variableExpressions.get(i), operator);
                }
            } else {
                if(operator.equals(Operator.MINUS)){
                    resultNumber = -resultNumber;
                } else if(operator.equals(Operator.DIVIDE)){
                    resultNumber = 1/resultNumber;
                }

                boolean checkResultNumber = false;
                if(variableExpressions.size() != (extendedOperands.size() + 2)){
                    if(leftOperator instanceof NumberLiteral || rightOperator instanceof NumberLiteral){
                        checkResultNumber = true;
                    }
                    for(Object expression1 : extendedOperands){
                        if(expression1 instanceof NumberLiteral){
                            checkResultNumber = true;
                            break;
                        }
                    }
                    if(checkResultNumber){
                        iExpression =  createInfixExpression(variableExpressions.get(0), typeCasting(resultNumber), operator);
                    } else {
                        iExpression =  createInfixExpression(variableExpressions.get(0), StringLiteralSymbolic.createStringLiteral(resultString), operator);
                    }
                    for(int i = 1; i < variableExpressions.size(); i++){
                        iExpression = createInfixExpression(iExpression, variableExpressions.get(i), operator);
                    }
                } else {
                    iExpression = createInfixExpression(leftOperator, rightOperator, operator);
                    for(int i = 2; i < variableExpressions.size(); i++){
                        iExpression = createInfixExpression(iExpression, variableExpressions.get(i), operator);
                    }
                }
            }
            return iExpression;
        }
    }

    public static InfixExpression createInfixExpression(Expression leftOperator, Expression rightOperator, InfixExpression.Operator operator){
        AST ast = AST.newAST(AST.JLS8);
        InfixExpression iExpression = ast.newInfixExpression();
        Expression left = (Expression) ASTNode.copySubtree(ast, leftOperator);
        Expression right = (Expression) ASTNode.copySubtree(ast, rightOperator);
        iExpression.setLeftOperand(left);
        iExpression.setRightOperand(right);
        iExpression.setOperator(operator);
        return iExpression;
    }

    public NumberLiteral typeCasting(double value){
        if (type == null || type.toString().equals("double") || type.toString().equals("float")){
            return NumberLiteralSymbolic.createNumberLiteral(Double.toString(value));
        } else if (type.toString().equals("int") || type.toString().equals("long")){
            int resultNumber = (int) value;
            return NumberLiteralSymbolic.createNumberLiteral(Integer.toString(resultNumber));
        } else {
            throw new RuntimeException("Kieu du lieu khong hop le");
        }
    }

    public Expression getLeftOperator() {
        return leftOperator;
    }

    public void setLeftOperator(Expression leftOperator) {
        this.leftOperator = leftOperator;
    }

    public Expression getRightOperator() {
        return rightOperator;
    }

    public void setRightOperator(Expression rightOperator) {
        this.rightOperator = rightOperator;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public InfixExpression getInfixExpression() {
        return infixExpression;
    }

    public void setInfixExpression(InfixExpression infixExpression) {
        this.infixExpression = infixExpression;
    }

    public List getExtendedOperands() {
        return extendedOperands;
    }

    public void setExtendedOperands(List extendedOperands) {
        this.extendedOperands = extendedOperands;
    }
}
