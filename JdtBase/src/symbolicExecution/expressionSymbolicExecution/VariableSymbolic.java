package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import symbolicExecution.Variable;

import java.util.List;

public class VariableSymbolic extends ExpressionSymbolic{
    private Name name;
    public VariableSymbolic(Name name, List<Variable> variables){
        super(name, variables);
        this.name = name;
    }

    @Override
    public Expression symbolicExecutionExpression() {
        Variable variable = findNameInVariables();
        if(variable != null){
            setType(variable.getType());
            if(variable.getValue() != null){
                return variable.getValue();
            }
        }
        return name;
    }

    private Variable findNameInVariables(){
        for(Variable variable : variables){
            if(variable.getName().toString().equals(expression.toString())){
                return variable;
            }
        }
        return null;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
