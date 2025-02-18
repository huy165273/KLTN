package symbolicExecution.nodeSymbolicExecution;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;
import symbolicExecution.Variable;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeSymbolic {
    private Statement statement;
    protected List<Variable> variables;

    public NodeSymbolic (Statement statement, List<Variable> variables){
        this.variables = variables;
        this.statement = statement;
    }

    public abstract Expression symbolicExecutionNode(int scope);

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
