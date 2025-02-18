package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class BreakCfgNode extends SimpleCfgNode{
    public BreakCfgNode(Statement statement) {
        super(statement);
    }
}
