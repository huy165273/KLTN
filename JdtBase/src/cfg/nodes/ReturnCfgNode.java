package cfg.nodes;

import org.eclipse.jdt.core.dom.Statement;

public class ReturnCfgNode extends SimpleCfgNode{
    public ReturnCfgNode (Statement statement){
        super(statement);
    }
}
