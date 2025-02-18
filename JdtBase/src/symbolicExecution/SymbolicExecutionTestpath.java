package symbolicExecution;

import cfg.nodes.*;
import org.eclipse.jdt.core.dom.*;
import symbolicExecution.expressionSymbolicExecution.*;
import symbolicExecution.nodeSymbolicExecution.DeclarationStatementSymbolic;
import symbolicExecution.nodeSymbolicExecution.ExpressionStatementSymbolic;

import java.util.ArrayList;
import java.util.List;

public class SymbolicExecutionTestpath {
    private List<ICfgNode> testpath = new ArrayList<>();
    private List parameters;
    private List<Variable> variables;

    public SymbolicExecutionTestpath (List<ICfgNode> testpath, List parameters){
        this.testpath = testpath;
        this.parameters = parameters;
        variables = new ArrayList<>();
    }

    public SymbolicExecutionTestpath(){}

    public List<Expression> symbolicExecution(){
        // Nếu trả về chỉ có 1 phần tử BooleanLiteral có giá trị là false thì đường thi hành không thể đi qua
        // Nếu trả về không có phần tử nào thì đường thi hành luôn được đi qua
        List<Expression> conditionPaths = new ArrayList<>();
        List<Expression> conditionSwitchs = new ArrayList<>();
        Expression switchExpression = null;
        int scope = 0;

        // khởi tạo cho parameters
        declarationParameters();
        // Duyệt từng node
        for(int i = 0 ; i < testpath.size() ; i++){
//            int index = testpath.indexOf(node);
            ICfgNode node = testpath.get(i);

            // Câu lệnh khởi tạo
            if(node instanceof DeclarationStatementCfgNode){
                Statement stm = ((DeclarationStatementCfgNode) node).getStatement();
                DeclarationStatementSymbolic dss = new DeclarationStatementSymbolic((VariableDeclarationStatement) stm, variables);
                dss.symbolicExecutionNode(scope);
            }

            // câu lệnh gán, câu lệnh in, .....
            else if (node instanceof ExpressionStatementCfgNode) {
                Statement stm = ((ExpressionStatementCfgNode) node).getStatement();
                ExpressionStatementSymbolic ess = new ExpressionStatementSymbolic((ExpressionStatement) stm, variables);
                Expression condition = ess.symbolicExecutionNode(scope);
                if(condition != null){
                    conditionPaths.add(condition);
                }
            }
            // Câu lệnh điều kiện, lặp
            else if (node instanceof ConditionCfgNode) {
                AST ast = AST.newAST(AST.JLS8);
                if(node instanceof SwitchCfgNode) {
                    switchExpression = ((ConditionCfgNode) node).getAstCondition();
//                    System.out.println(switchExpression);
                    if(switchExpression instanceof InfixExpression){
                        InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) switchExpression, variables);
                        switchExpression = infixExpressionSymbolic.symbolicExecutionExpression();
                    }
                } else {
                    Expression expression = ((ConditionCfgNode) node).getAstCondition();
                    ConditionSymbolic conditionSymbolic = new ConditionSymbolic(expression, variables);
                    expression = conditionSymbolic.symbolicExecutionExpression();
                    if (expression == null){
                        conditionPaths = new ArrayList<>();
                        conditionPaths.add(ast.newBooleanLiteral(false));
                        return conditionPaths;
                    }

                    if(testpath.get(i + 1) == node.getTrueNode()){
                        if(expression instanceof BooleanLiteral){
                            boolean result = ((BooleanLiteral) expression).booleanValue();
                            if(!result){
                                conditionPaths = new ArrayList<>();
                                conditionPaths.add(ast.newBooleanLiteral(false));
                                return conditionPaths;
                            }
                        } else {
                            Expression condition = AssignmentSymbolic.createAssignment(expression, ast.newBooleanLiteral(true), Assignment.Operator.ASSIGN);
                            conditionPaths.add(condition);
                        }
                    } else {
                        if(expression instanceof BooleanLiteral){
                            boolean result = ((BooleanLiteral) expression).booleanValue();
                            if(result){
                                conditionPaths = new ArrayList<>();
                                conditionPaths.add(ast.newBooleanLiteral(false));
                                return conditionPaths;
                            }
                        } else {
                            Expression condition = AssignmentSymbolic.createAssignment(expression, ast.newBooleanLiteral(false), Assignment.Operator.ASSIGN);
                            conditionPaths.add(condition);
                        }
                    }
                }

            }
             else if(node instanceof SimpleCfgNode){
                Expression expression = ((SimpleCfgNode) node).getExpression();
                Statement statement = ((SimpleCfgNode) node).getStatement();
                if(expression instanceof VariableDeclarationExpression){
                    VariableDeclarationSymbolic variableDeclarationSymbolic = new VariableDeclarationSymbolic((VariableDeclarationExpression) expression, variables, scope);
                    variableDeclarationSymbolic.symbolicExecutionExpression();
                } else if (expression instanceof PostfixExpression ){
                    PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) expression, variables);
                    postfixExpressionSymbolic.symbolicExecutionExpression();
                } else if (expression instanceof Assignment) {
                    AssignmentSymbolic ass = new AssignmentSymbolic((Assignment) expression, variables);
                    ass.symbolicExecutionExpression();
                }

                if(statement instanceof SwitchCase){
                    SwitchCase switchCase = (SwitchCase) statement;
                    expression = switchCase.getExpression();

                    if(switchCase.isDefault()){
                        conditionPaths.addAll(conditionSwitchs);
                    } else if(testpath.get(i + 1) == node.getTrueNode()){
                        conditionSwitchs = new ArrayList<>();
                        InfixExpression infixExpression = InfixExpressionSymbolic.createInfixExpression(switchExpression, expression, InfixExpression.Operator.EQUALS);
                        conditionPaths.add(infixExpression);
                    } else {
                        InfixExpression infixExpression = InfixExpressionSymbolic.createInfixExpression(switchExpression, expression, InfixExpression.Operator.NOT_EQUALS);
                        conditionSwitchs.add(infixExpression);
                    }
                }
            }
            // Câu lệnh mở, đóng khối
            else if (node instanceof ScopeCfgNode) {
                ScopeCfgNode scopeCfgNode = (ScopeCfgNode) node;
                if(scopeCfgNode.getContent().equals("{")){
                    scope++;
                } else {
                    deleteVariableOutScope(scope);
                    scope--;
                }
            }
        }
//        for(Variable variable : variables){
//            if(variable.getIndex() == -1){
//                System.out.println(variable.getName() + " = " + variable.getValue());
//            } else {
//                System.out.println(variable.getName() + "[" + variable.getIndex() + "]" + " = " + variable.getValue());
//            }
//        }
//        System.out.println(conditionPaths);
        return conditionPaths;
    }

    private void declarationParameters(){
        for(Object parameter : parameters){
            if(parameter instanceof SingleVariableDeclaration){
                SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) parameter;
                Type varType = singleVariableDeclaration.getType();
                Expression name = singleVariableDeclaration.getName();
                variables.add(new Variable(name, 0, varType, null));
            }
        }
    }

    private void deleteVariableOutScope(int scope){
        variables.removeIf(variable -> variable.getScope() == scope);
    }


    public List<ICfgNode> getTestpath() {
        return testpath;
    }

    public void setTestpath(List<ICfgNode> testpath) {
        this.testpath = testpath;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }
}
