package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class VariableDeclarationSymbolic extends ExpressionSymbolic{
    private VariableDeclarationExpression varDE;
    private int scope;
    private Type varType;
    private List fragments ;
    public VariableDeclarationSymbolic(VariableDeclarationExpression variableDeclarationExpression, List<Variable> variables, int scope){
        super(variableDeclarationExpression, variables);
        this.varDE = variableDeclarationExpression;
        this.scope = scope;
        this.varType = variableDeclarationExpression.getType();
        this.fragments = variableDeclarationExpression.fragments();
    }

    @Override
    public Expression symbolicExecutionExpression() {

        if(varType instanceof PrimitiveType){
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    removeVariable(name.toString(), scope);
                    variables.add(new Variable(name, scope, varType, variableDeclarationFragment.getInitializer()));
                } else {
                    Expression name = (Expression) fragments.get(i);
                    variables.add(new Variable(name, scope, varType, null));
                }
            }
        } else if (varType.toString().equals("String")) {
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    removeVariable(name.toString(), scope);
                    variables.add(new Variable(name, scope, varType, variableDeclarationFragment.getInitializer()));
                } else {
                    Expression name =(Expression) fragments.get(i);
                    variables.add(new Variable(name, scope, varType, null));
                }
            }
        } else if (varType instanceof ArrayType) {
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    Expression variableValue = variableDeclarationFragment.getInitializer();

                    if (variableValue instanceof ArrayCreation) {
                        ArrayCreationSymbolic arrayCreationSymbolic = new ArrayCreationSymbolic((ArrayCreation) variableValue, variables, name, scope);
                        arrayCreationSymbolic.symbolicExecutionExpression();
                    }
                }
            }
        }

        return null;
    }

    private void removeVariable(String name, int scope){
        for(int i = 0; i < variables.size(); i++){
            if(name.equals(variables.get(i).getName().toString()) && variables.get(i).getScope() == scope){
                variables.remove(i);
                i--;
            }
        }
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Type getVarType() {
        return varType;
    }

    public void setVarType(Type varType) {
        this.varType = varType;
    }

    public VariableDeclarationExpression getVarDE() {
        return varDE;
    }

    public void setVarDE(VariableDeclarationExpression varDE) {
        this.varDE = varDE;
    }

    public List getFragments() {
        return fragments;
    }

    public void setFragments(List fragments) {
        this.fragments = fragments;
    }
}
