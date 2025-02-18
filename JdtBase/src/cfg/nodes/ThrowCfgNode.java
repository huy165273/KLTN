package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class ThrowCfgNode extends SimpleCfgNode{
    public ThrowCfgNode(Statement statement) {
        super(statement);
    }
}
