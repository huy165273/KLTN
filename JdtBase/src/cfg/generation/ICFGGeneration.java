package cfg.generation;
import cfg.ICFG;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public interface ICFGGeneration {
    final static int IF_FLAG = 0;

    final static int DO_FLAG = 1;

    final static int WHILE_FLAG = 2;

    final static int FOR_FLAG = 3;

    final static int SEPARATE_FOR_INTO_SEVERAL_NODES = 1;

    final static int DONOT_SEPARATE_FOR = 0;

    ICFG generateCFG();
    MethodDeclaration getFunctionNode();
    void setFunctionNode(MethodDeclaration functionNode);

}