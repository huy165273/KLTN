package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class InfixConditionSymbolic extends ExpressionSymbolic{
    private InfixExpression infixExpression;
    private Expression leftOperator;
    private InfixExpression.Operator operator;
    private Expression rightOperator;
    public InfixConditionSymbolic(InfixExpression infixExpression, List<Variable> variables){
        super(infixExpression, variables);
        this.infixExpression = infixExpression;
        this.operator = infixExpression.getOperator();
        this.leftOperator = infixExpression.getLeftOperand();
        this.rightOperator = infixExpression.getRightOperand();
    }

    @Override
    public Expression symbolicExecutionExpression() {
        if(leftOperator instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) leftOperator, variables);
            leftOperator = arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (leftOperator instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) leftOperator, variables);
            leftOperator = infixExpressionSymbolic.symbolicExecutionExpression();
            setType(infixExpressionSymbolic.getType());
        } else if(leftOperator instanceof  PostfixExpression){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) leftOperator, variables);
            leftOperator = postfixExpressionSymbolic.symbolicExecutionExpression();
        } else if (leftOperator instanceof Name) {
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) leftOperator, variables);
            leftOperator = variableSymbolic.symbolicExecutionExpression();
            setType(variableSymbolic.getType());
        }

        if(rightOperator instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) rightOperator, variables);
            rightOperator = arrayAccessSymbolic.symbolicExecutionExpression();
            setType(arrayAccessSymbolic.getType());
        } else if (rightOperator instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) rightOperator, variables);
            rightOperator = infixExpressionSymbolic.symbolicExecutionExpression();
        } else if(rightOperator instanceof  PostfixExpression){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) rightOperator, variables);
            rightOperator = postfixExpressionSymbolic.symbolicExecutionExpression();
            setType(postfixExpressionSymbolic.getType());
        } else if (rightOperator instanceof Name) {
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) rightOperator, variables);
            rightOperator = variableSymbolic.symbolicExecutionExpression();
            setType(variableSymbolic.getType());
        }

        if((leftOperator instanceof NumberLiteral) && (rightOperator instanceof NumberLiteral)){
            return calculateNumberCondition(leftOperator, operator, rightOperator);
        } else if((leftOperator instanceof CharacterLiteral) && (rightOperator instanceof CharacterLiteral)){
            return calculateCharacterCondition(leftOperator, operator, rightOperator);
        } else if((leftOperator instanceof BooleanLiteral) && (rightOperator instanceof BooleanLiteral)){
            return calculateBooleanCondition(leftOperator, operator, rightOperator);
        }

        return InfixExpressionSymbolic.createInfixExpression(leftOperator, rightOperator, operator);
    }

    public BooleanLiteral calculateNumberCondition(Expression leftOperator, InfixExpression.Operator operator, Expression rightOperator){
        double leftValue = Double.parseDouble(((NumberLiteral) leftOperator).getToken());
        double rightValue = Double.parseDouble(((NumberLiteral) rightOperator).getToken());
        boolean result = true;
        switch (operator.toString()){
            case ">":
                result = leftValue > rightValue;
                break;
            case "<":
                result = leftValue < rightValue;
                break;
            case "==":
                result = leftValue == rightValue;
                break;
            case ">=":
                result = leftValue >= rightValue;
                break;
            case "<=":
                result = leftValue <= rightValue;
                break;
            case "!=":
                result = leftValue != rightValue;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        return BooleanLiteralSymbolic.createBooleanLiteral(result);
    }

    public BooleanLiteral calculateCharacterCondition(Expression leftOperator, InfixExpression.Operator operator, Expression rightOperator){
        char leftValue = ((CharacterLiteral) leftOperator).charValue();
        char rightValue = ((CharacterLiteral) rightOperator).charValue();
        boolean result = true;
        if (operator.equals(InfixExpression.Operator.GREATER)) {
            result = leftValue > rightValue;
        } else if (operator.equals(InfixExpression.Operator.LESS)) {
            result = leftValue < rightValue;
        } else if (operator.equals(InfixExpression.Operator.EQUALS)) {
            result = leftValue == rightValue;
        } else if (operator.equals(InfixExpression.Operator.GREATER_EQUALS)) {
            result = leftValue >= rightValue;
        } else if (operator.equals(InfixExpression.Operator.LESS_EQUALS)) {
            result = leftValue <= rightValue;
        } else if (operator.equals(InfixExpression.Operator.NOT_EQUALS)) {
            result = leftValue != rightValue;
        } else {
            throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        return BooleanLiteralSymbolic.createBooleanLiteral(result);
    }

    public BooleanLiteral calculateBooleanCondition(Expression leftOperator, InfixExpression.Operator operator, Expression rightOperator){
        boolean leftValue = ((BooleanLiteral) leftOperator).booleanValue();
        boolean rightValue = ((BooleanLiteral) rightOperator).booleanValue();
        boolean result = true;
        switch (operator.toString()){
            case "==":
                result = leftValue == rightValue;
                break;
            case "!=":
                result = leftValue != rightValue;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }

        return BooleanLiteralSymbolic.createBooleanLiteral(result);
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

    public InfixExpression.Operator getOperator() {
        return operator;
    }

    public void setOperator(InfixExpression.Operator operator) {
        this.operator = operator;
    }

    public InfixExpression getInfixExpression() {
        return infixExpression;
    }

    public void setInfixExpression(InfixExpression infixExpression) {
        this.infixExpression = infixExpression;
    }
}
