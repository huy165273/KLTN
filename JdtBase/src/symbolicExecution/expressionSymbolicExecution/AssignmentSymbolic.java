package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class AssignmentSymbolic extends ExpressionSymbolic {
    private Assignment assignment;
    private Expression leftAssignment;
    private Expression rightAssignment;
    private Assignment.Operator operator;

    public AssignmentSymbolic(Assignment assignment, List<Variable> variables){
        super(assignment, variables);
        this.assignment = assignment;
        rightAssignment = assignment.getRightHandSide();
        operator = assignment.getOperator();
        leftAssignment = assignment.getLeftHandSide();
    }

    @Override
    public Expression symbolicExecutionExpression() {
        Variable variable = leftAssignmentSymbolic();
        Expression value = rightAssignmentSymbolic();

        if(variable != null){
            for (Variable item : variables) {
                if (variable.getName().toString().equals(item.getName().toString()) && variable.getIndex() == item.getIndex()) {
                    item.setValue(value);
                    break;
                }
            }
        } else {
            return createAssignment(leftAssignment, rightAssignment, operator);
        }
        return null;
    }

    private Variable leftAssignmentSymbolic(){
        if(leftAssignment instanceof ArrayAccess){
            ArrayAccessSymbolic aas = new ArrayAccessSymbolic((ArrayAccess) leftAssignment, variables);
            Expression array = aas.getArray();
            Expression index = aas.getIndex();

            if(array instanceof ArrayAccess){
                ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) array, variables);
                array = arrayAccessSymbolic.symbolicExecutionExpression();
            }

            if(index instanceof ArrayAccess){
                ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) index, variables);
                index = arrayAccessSymbolic.symbolicExecutionExpression();
            } else if (index instanceof InfixExpression) {
                InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) index, variables);
                index = infixExpressionSymbolic.symbolicExecutionExpression();
            } else if (index instanceof Name) {
                VariableSymbolic variableSymbolic = new VariableSymbolic((Name) index, variables);
                index = variableSymbolic.symbolicExecutionExpression();
            }

            if(index instanceof NumberLiteral){
                int indexInt = Integer.parseInt(((NumberLiteral) index).getToken());
                for (Variable var : variables) {
                    if (indexInt == var.getIndex() && aas.getArray().toString().equals(var.getName().toString())) {
                        leftAssignment = var.getValue();
                        return var;
                    }
                }
            } else {
                leftAssignment = ArrayAccessSymbolic.createArrayAccess(array, index);
            }
        } else if (leftAssignment instanceof Name) {
            for (Variable var : variables) {
                if (leftAssignment.toString().equals(var.getName().toString())) {
                    leftAssignment = var.getValue();
                    return var;
                }
            }
        }

        return null;
    }

    private Expression rightAssignmentSymbolic(){
        if (rightAssignment instanceof Name){
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) rightAssignment, variables);
            return variableSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof ArrayCreation) {
            ArrayCreationSymbolic arrayCreationSymbolic = new ArrayCreationSymbolic((ArrayCreation) rightAssignment, variables, leftAssignment);
            return arrayCreationSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof ArrayAccess) {
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) rightAssignment, variables);
            return arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof InfixExpression){
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) rightAssignment, variables);
            return infixExpressionSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof ParenthesizedExpression) {
            ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) rightAssignment, variables);
            return parenthesizedExpressionSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof NumberLiteral) {
            NumberLiteralSymbolic numberLiteralSymbolic = new NumberLiteralSymbolic(rightAssignment, variables);
            return numberLiteralSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof StringLiteral) {
            StringLiteralSymbolic stringLiteralSymbolic = new StringLiteralSymbolic(rightAssignment, variables);
            return stringLiteralSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof CharacterLiteral) {
            CharacterLiteralSymbolic characterLiteralSymbolic = new CharacterLiteralSymbolic(rightAssignment, variables);
            return characterLiteralSymbolic.symbolicExecutionExpression();
        } else if (rightAssignment instanceof BooleanLiteral) {
            BooleanLiteralSymbolic booleanLiteralSymbolic = new BooleanLiteralSymbolic(rightAssignment, variables);
            return booleanLiteralSymbolic.symbolicExecutionExpression();
        } else if(rightAssignment instanceof  PostfixExpression){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) rightAssignment, variables);
            postfixExpressionSymbolic.symbolicExecutionExpression();
        }
        return rightAssignment;
    }

    public static Assignment createAssignment(Expression leftAssignment, Expression rightAssignment, Assignment.Operator operator){
        AST ast = AST.newAST(AST.JLS8);
        Assignment assignment1 = ast.newAssignment();
        Expression left = (Expression) ASTNode.copySubtree(ast, leftAssignment);
        Expression right = (Expression) ASTNode.copySubtree(ast, rightAssignment);
        assignment1.setLeftHandSide(left);
        assignment1.setRightHandSide(right);
        assignment1.setOperator(operator);
        return assignment1;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setLeftAssignment(Expression leftAssignment) {
        this.leftAssignment = leftAssignment;
    }

    public Expression getLeftAssignment() {
        return leftAssignment;
    }

    public void setRightAssignment(Expression rightAssignment) {
        this.rightAssignment = rightAssignment;
    }

    public Expression getRightAssignment() {
        return rightAssignment;
    }

    public void setOperator(Assignment.Operator operator) {
        this.operator = operator;
    }

    public Assignment.Operator getOperator() {
        return operator;
    }
}
