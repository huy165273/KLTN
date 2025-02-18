package symbolicExecution.nodeSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;
import symbolicExecution.expressionSymbolicExecution.ArrayAccessSymbolic;
import symbolicExecution.expressionSymbolicExecution.ArrayCreationSymbolic;
import symbolicExecution.expressionSymbolicExecution.NumberLiteralSymbolic;
import symbolicExecution.expressionSymbolicExecution.StringLiteralSymbolic;

import java.util.ArrayList;
import java.util.List;

public class DeclarationStatementSymbolic extends NodeSymbolic {
    private VariableDeclarationStatement varSmt;
    private Type varType;
    private List fragments ;

    public DeclarationStatementSymbolic(VariableDeclarationStatement variableDeclarationStatement, List<Variable> variables){
        super(variableDeclarationStatement, variables);
        this.varSmt = variableDeclarationStatement;
        varType = varSmt.getType();
        fragments = varSmt.fragments();
    }

    @Override
    public Expression symbolicExecutionNode(int scope) {
        if(varType instanceof PrimitiveType){
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    variables.add(new Variable(name, scope, varType, variableDeclarationFragment.getInitializer()));
                } else {
                    Expression name = (Expression) varSmt.fragments().get(i);
                    variables.add(new Variable(name, scope, varType, null));
                }
            }
        } else if (varType.toString().equals("String")) {
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    variables.add(new Variable(name, scope, varType, variableDeclarationFragment.getInitializer()));
                } else {
                    Expression name =(Expression) varSmt.fragments().get(i);
                    variables.add(new Variable(name, scope, varType, null));
                }
            }
        } else if (varType instanceof ArrayType) {
            for (int i = 0; i < fragments.size(); i ++){
                if(fragments.get(i) instanceof VariableDeclarationFragment){
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(i);
                    Expression name = variableDeclarationFragment.getName();
                    Expression variableValue = variableDeclarationFragment.getInitializer();

                    if (variableValue instanceof ArrayInitializer) {
                        List values = ((ArrayInitializer) variableValue).expressions();
                        for(int j = 0; j < values.size(); j++){
                            variables.add(new Variable(name, j,  scope, varType,(Expression) values.get(j)));
                        }
//                        ArrayCreationSymbolic arrayCreationSymbolic = new ArrayCreationSymbolic((ArrayCreation) variableValue, variables, name, scope);
//                        arrayCreationSymbolic.symbolicExecutionExpression();
                    }
                }
            }
        }
        return null;
    }

    public Type getVarType() {
        return varType;
    }

    public void setVarType(Type varType) {
        this.varType = varType;
    }

    public void setVarSmt(VariableDeclarationStatement varSmt) {
        this.varSmt = varSmt;
    }

    public VariableDeclarationStatement getVarSmt() {
        return varSmt;
    }

    public List getFragments() {
        return fragments;
    }

    public void setFragments(List fragments) {
        this.fragments = fragments;
    }
}
