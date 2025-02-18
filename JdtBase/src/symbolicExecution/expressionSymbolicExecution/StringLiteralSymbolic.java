package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.StringLiteral;
import symbolicExecution.Variable;

import java.util.List;

public class StringLiteralSymbolic extends ExpressionSymbolic {
    public StringLiteralSymbolic (Expression expression, List<Variable> variables){
        super(expression, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {

        return expression;
    }

    public static StringLiteral createStringLiteral(String value){
        AST ast = AST.newAST(AST.JLS8);
        StringLiteral stringLiteral = ast.newStringLiteral();
        stringLiteral.setLiteralValue(value);
        return stringLiteral;
    }
}
