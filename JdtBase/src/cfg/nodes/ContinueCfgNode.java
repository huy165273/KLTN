package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class ContinueCfgNode extends SimpleCfgNode{

    public ContinueCfgNode(Statement statement) {
        super(statement);
    }
}
