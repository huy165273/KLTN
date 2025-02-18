package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.ArrayList;
import java.util.List;

public class ArrayCreationSymbolic extends ExpressionSymbolic{
    private ArrayCreation arrayCreation;
    private Expression name;

    private Type varType;

    private int scope;
    public ArrayCreationSymbolic(ArrayCreation arrayCreation, List<Variable> variables, Expression name, int scope){
        super(arrayCreation, variables);
        this.arrayCreation = arrayCreation;
        this.name = name;
        this.varType = arrayCreation.getType();
        this.scope = scope;
    }

    public ArrayCreationSymbolic(ArrayCreation arrayCreation, List<Variable> variables, Expression name){
        super(arrayCreation, variables);
        this.arrayCreation = arrayCreation;
        this.name = name;
        this.varType = arrayCreation.getType();
        scope = -1;
    }

    @Override
    public Expression symbolicExecutionExpression() {
        for(int i = 0; i < variables.size(); i++){
            if(name.toString().equals(variables.get(i).getName().toString())){
                scope = variables.get(i).getScope();
                variables.remove(i);
                i--;
            }
        }
        ArrayInitializer arrayInitializer = arrayCreation.getInitializer();

        List dimensions = arrayCreation.dimensions();
        if(dimensions.isEmpty()){
            arrayInitializerValue(arrayInitializer, name);
        } else {
            arrayInitializerNull(dimensions, name);
        }
        return name;
    }

    private void arrayInitializerNull(List dimensions, Expression name){
        if(dimensions.isEmpty()){
            return;
        }
        int size = Integer.parseInt(((NumberLiteral) dimensions.get(0)).getToken());
        if(dimensions.size() == 1){
            for(int i = 0; i < size; i++){
                variables.add(new Variable(name, i, scope, varType, null ));
            }
        } else {
            for(int i = 0; i < size; i++){
                List dimensionsCopy = new ArrayList<>(dimensions);
                Expression value = ArrayAccessSymbolic.createArrayAccess(name, NumberLiteralSymbolic.createNumberLiteral(String.valueOf(i)));
                variables.add(new Variable(name, i, scope, varType, value ));
                dimensionsCopy.remove(0);
                arrayInitializerNull(dimensionsCopy, value);
            }
        }
    }

    private void arrayInitializerValue(ArrayInitializer arrayInitializer, Expression name){
        if(arrayInitializer != null){
            List expressionInitializers = arrayInitializer.expressions();
            for (int i = 0; i < expressionInitializers.size(); i++) {
                if (expressionInitializers.get(i) instanceof ArrayInitializer) {
                    Expression value = ArrayAccessSymbolic.createArrayAccess(name, NumberLiteralSymbolic.createNumberLiteral(String.valueOf(i)));
                    variables.add(new Variable(name, i, scope, varType, value));
                    arrayInitializerValue((ArrayInitializer) expressionInitializers.get(i), value);
                } else {
                    variables.add(new Variable(name, i, scope, varType,(Expression) expressionInitializers.get(i)));
                }
            }
        }
    }

    public ArrayCreation getArrayCreation() {
        return arrayCreation;
    }

    public void setArrayCreation(ArrayCreation arrayCreation) {
        this.arrayCreation = arrayCreation;
    }

    public void setName(Expression name) {
        this.name = name;
    }

    public Expression getName() {
        return name;
    }

    public void setVarType(Type varType) {
        this.varType = varType;
    }

    public Type getVarType() {
        return varType;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getScope() {
        return scope;
    }
}
