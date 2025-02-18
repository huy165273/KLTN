package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class PostfixExpressionSymbolic extends ExpressionSymbolic{
    private PostfixExpression.Operator operator;
    private Expression operand;
    public PostfixExpressionSymbolic(PostfixExpression postfixExpression, List<Variable> variables){
        super(postfixExpression, variables);
        operator = postfixExpression.getOperator();
        operand = postfixExpression.getOperand();
    }

    @Override
    public Expression symbolicExecutionExpression() {
        if(operand instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) operand, variables);
            operand = arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (operand instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) operand, variables);
            operand = infixExpressionSymbolic.symbolicExecutionExpression();
        } else if (operand instanceof ParenthesizedExpression) {
            ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) operand, variables);
            operand = parenthesizedExpressionSymbolic.symbolicExecutionExpression();
        }

        if (operand instanceof Name) {
            Expression value = new VariableSymbolic((Name) operand, variables).symbolicExecutionExpression();
            if(value instanceof NumberLiteral){
                if(operator.equals(PostfixExpression.Operator.INCREMENT)){
                    int result = Integer.parseInt(((NumberLiteral) value).getToken()) + 1;
                    setValueVariable(operand.toString(), NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result)));
                    return NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result));
                } else if(operator.equals(PostfixExpression.Operator.DECREMENT)){
                    int result = Integer.parseInt(((NumberLiteral) value).getToken()) - 1;
                    setValueVariable(operand.toString(), NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result)));
                    return NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result));
                } else {
                    throw new RuntimeException("Operator is invalid!");
                }
            } else {
                throw new RuntimeException("Operand " + operator + " is invalid!");
            }
        } else if(operand instanceof NumberLiteral){
            if(operator.equals(PostfixExpression.Operator.INCREMENT)){
                int result = Integer.parseInt(((NumberLiteral) operand).getToken()) + 1;
                return NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result));
            } else if(operator.equals(PostfixExpression.Operator.DECREMENT)){
                int result = Integer.parseInt(((NumberLiteral) operand).getToken()) - 1;
                return NumberLiteralSymbolic.createNumberLiteral(String.valueOf(result));
            } else {
                throw new RuntimeException("Operator is invalid!");
            }
        }
        return null;
    }

    private void setValueVariable(String name, Expression value){
        for (Variable variable : variables) {
            if (name.equals(variable.getName().toString())) {
                variable.setValue(value);
                setType(variable.getType());
                break;
            }
        }
    }

    public PostfixExpression.Operator getOperator() {
        return operator;
    }

    public void setOperator(PostfixExpression.Operator operator) {
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }
}
