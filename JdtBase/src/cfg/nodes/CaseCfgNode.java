package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class CaseCfgNode extends SimpleCfgNode{
    public CaseCfgNode(Statement statement) {
        super(statement);
    }
}
