package cfg.generation.testpath;

import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;

public interface ITestpath {
    public void setFunctionNode(MethodDeclaration node);
    public void push(ICfgNode node);
    public void pop();
    public ArrayList<ICfgNode> getElements() ;
    public void setFlags( ArrayList<FlagCondition> flags );
    public ArrayList<FlagCondition> getFlags();
    public MethodDeclaration getFunctionNode();
}
