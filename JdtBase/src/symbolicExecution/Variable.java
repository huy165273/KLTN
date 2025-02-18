package symbolicExecution;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Type;

public class Variable {
    private Expression name;

    private int index;

    private Type type;

    private Expression value;
    private int scope;

    public Variable (Expression name, int scope, Type type, Expression value){
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.value = value;
        this.index = -1;
    }

    public Variable (Expression name, int index, int scope, Type type, Expression value){
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.value = value;
        this.index = index;
    }

//    public boolean equalsVariable(Variable variable){
//        if(this)
//    }


    public void setName(Expression name) {
        this.name = name;
    }

    public Expression getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
