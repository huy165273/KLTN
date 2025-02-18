package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class DeclarationStatementCfgNode extends SimpleCfgNode {
    public DeclarationStatementCfgNode(Statement statement) {
        super(statement);
    }
}
