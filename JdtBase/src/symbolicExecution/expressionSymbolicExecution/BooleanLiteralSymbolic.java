package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.StringLiteral;
import symbolicExecution.Variable;

import java.util.List;

public class BooleanLiteralSymbolic extends ExpressionSymbolic{
    public BooleanLiteralSymbolic (Expression expression, List<Variable> variables){
        super(expression, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {
        return expression;
    }

    public static BooleanLiteral createBooleanLiteral(boolean value){
        AST ast = AST.newAST(AST.JLS8);
        return ast.newBooleanLiteral(value);
    }
}
