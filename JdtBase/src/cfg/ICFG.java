package cfg;

import cfg.nodes.ICfgNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;

public interface ICFG {
    ICfgNode findById();
    ICfgNode getBeginNode();
    double computeNumberOfNode();
    ArrayList<ICfgNode>  getAllNodes();
    void resetVisitedState();
    MethodDeclaration getFunctionNode();
    ICfgNode getRoot();
}
