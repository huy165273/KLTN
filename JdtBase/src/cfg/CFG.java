package cfg;

import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;

public class CFG implements ICFG{
    private MethodDeclaration _functionNode;
    private ICfgNode _root;
    private ArrayList<ICfgNode> _statements ;

    public CFG (ArrayList<ICfgNode> statements,  MethodDeclaration functionNode) {
        this._statements = statements;
        this._functionNode = functionNode;
    }

    public CFG (ArrayList<ICfgNode> statements) {
        this._statements = statements;
        this._functionNode = null;
    }

    public CFG (ICFG cfg){
        this._functionNode = cfg.getFunctionNode();
        this._root = cfg.getRoot();
        this._statements = new ArrayList<>(cfg.getAllNodes());
    }


    public void resetVisitedState(){
        this.getAllNodes().forEach((value) -> value.setVisited(false));
    }

    public double computeNumberOfNode() {
        return 0;
    };

    public ICfgNode findById(){
        return null;
    };
    public ICfgNode getBeginNode() {
        return this._root;
    };
    public  String printInfor(){
        return "Function name:" + this._functionNode.getName() + " . Statements  size: " + this._statements.size();
    }

    public ArrayList<ICfgNode> getAllNodes () {
        return this._statements;
    };

     public ArrayList<ICfgNode> statements() {
        return this._statements;
    }

    public void statements(ArrayList<ICfgNode> value) {
        this._statements = value;
    }
    public MethodDeclaration getFunctionNode() {
        return this._functionNode;
    }

    public void setFunctionNode(MethodDeclaration value) {
        this._functionNode = value;
    }

    public ICfgNode getRoot() {
        return this._root;
    }

    public void setRoot(ICfgNode root) {
        this._root = root;
    }
}
