package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Type;
import symbolicExecution.Variable;

import java.util.List;

public abstract class ExpressionSymbolic {
    protected Expression expression;
    protected Type type;
    protected List<Variable> variables;
    public ExpressionSymbolic(Expression expression, List<Variable> variables){
        this.expression = expression;
        this.variables = variables;
        this.type = null;
    }
    public abstract Expression symbolicExecutionExpression();

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
