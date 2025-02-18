package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Name;
import symbolicExecution.Variable;

import java.util.List;

public class ConditionSymbolic extends ExpressionSymbolic{
    public ConditionSymbolic (Expression expression, List<Variable> variables){
        super(expression, variables);
    }

    @Override
    public Expression symbolicExecutionExpression() {
        if(expression instanceof InfixExpression){
            InfixConditionSymbolic infixConditionSymbolic = new InfixConditionSymbolic((InfixExpression) expression, variables);
            return infixConditionSymbolic.symbolicExecutionExpression();

        } else if(expression instanceof Name){
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) expression, variables);
            return  variableSymbolic.symbolicExecutionExpression();
        }
        return null;
    }

}
