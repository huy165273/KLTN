package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.NumberLiteral;
import symbolicExecution.Variable;

import java.util.List;

public class NumberLiteralSymbolic extends ExpressionSymbolic{
    public NumberLiteralSymbolic (Expression expression, List<Variable> variables){
        super(expression, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {
        return expression;
    }

    public static  NumberLiteral createNumberLiteral(String value) {
        AST ast = AST.newAST(AST.JLS8);
        NumberLiteral numberLiteral = ast.newNumberLiteral();
        numberLiteral.setToken(value);
        return numberLiteral;
    }
}
