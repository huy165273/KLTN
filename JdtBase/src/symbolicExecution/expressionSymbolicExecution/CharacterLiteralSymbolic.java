package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.StringLiteral;
import symbolicExecution.Variable;

import java.util.List;

public class CharacterLiteralSymbolic extends ExpressionSymbolic {
    public CharacterLiteralSymbolic (Expression expression, List<Variable> variables){
        super(expression, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {

        return expression;
    }

    public static CharacterLiteral createCharacterLiteral(char value){
        AST ast = AST.newAST(AST.JLS8);
        CharacterLiteral characterLiteral = ast.newCharacterLiteral();
        characterLiteral.setCharValue(value);
        return characterLiteral;
    }
}
