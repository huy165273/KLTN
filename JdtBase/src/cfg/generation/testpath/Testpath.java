package cfg.generation.testpath;

import cfg.ICFG;
import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;

public class Testpath implements ITestpath{
    private ArrayList<ICfgNode> _elements;
    private ICFG _cfg;
    private MethodDeclaration _functionNode;
    private ArrayList<FlagCondition> _flags;


    public void push (ICfgNode node) {
        if (this._elements == null) {
            this._elements = new ArrayList<ICfgNode>();
            this._elements.add(node);
        } else {
            this._elements.add(node);
        }
    }

    public void pop() {
        this._elements.remove(this._elements.size() - 1);
    }

    public ArrayList<ICfgNode> getElements()  {
        return this._elements;
    }

    public void setElements(ArrayList<ICfgNode> value) {
        this._elements = value;
    }

    public ICFG getCfg(){
        return this._cfg;
    }

    public void setCfg(ICFG value) {
        this._cfg = value;
    }

    public MethodDeclaration getFunctionNode() {
        return this._functionNode;
    }

    public void setFunctionNode(MethodDeclaration value) {
        this._functionNode = value;
    }

    public ArrayList<FlagCondition> getFlags() {
        return this._flags;
    }

    public void setFlags(ArrayList<FlagCondition> value) {
        this._flags = value;
    }
}
