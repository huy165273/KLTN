package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class ParenthesizedExpressionSymbolic extends ExpressionSymbolic {
    public ParenthesizedExpressionSymbolic(ParenthesizedExpression parenthesizedExpression, List<Variable> variables){
        super(parenthesizedExpression.getExpression(), variables);
    }

    public ParenthesizedExpressionSymbolic(List<Variable> variables){
        super(null, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {
        if (expression instanceof Name){
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) expression, variables);
            expression = variableSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof NumberLiteral) {
            NumberLiteralSymbolic numberLiteralSymbolic = new NumberLiteralSymbolic(expression, variables);
            return numberLiteralSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof StringLiteral) {
            StringLiteralSymbolic stringLiteralSymbolic = new StringLiteralSymbolic(expression, variables);
            return stringLiteralSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof CharacterLiteral) {
            CharacterLiteralSymbolic characterLiteralSymbolic = new CharacterLiteralSymbolic(expression, variables);
            return characterLiteralSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof BooleanLiteral) {
            BooleanLiteralSymbolic booleanLiteralSymbolic = new BooleanLiteralSymbolic(expression, variables);
            return booleanLiteralSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof ArrayAccess) {
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) expression, variables);
            expression = arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (expression instanceof InfixExpression){
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) expression, variables);
            expression = infixExpressionSymbolic.symbolicExecutionExpression();
//            System.out.println(expression);
        } else if (expression instanceof ParenthesizedExpression) {
            ParenthesizedExpressionSymbolic parenthesizedExpressionSymbolic = new ParenthesizedExpressionSymbolic((ParenthesizedExpression) expression, variables);
            expression = parenthesizedExpressionSymbolic.symbolicExecutionExpression();
        }

        if(!(expression instanceof NumberLiteral) && !(expression instanceof CharacterLiteral) && !(expression instanceof BooleanLiteral) && !(expression instanceof StringLiteral)){
            AST ast = AST.newAST(AST.JLS8);
            ParenthesizedExpression parenthesizedExpression = ast.newParenthesizedExpression();
            Expression expressionCopy = (Expression) ASTNode.copySubtree(ast, expression);
            parenthesizedExpression.setExpression(expressionCopy);
            return parenthesizedExpression;
        }
        return expression;
    }
}
