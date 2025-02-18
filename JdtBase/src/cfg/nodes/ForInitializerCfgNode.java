package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;

public class ForInitializerCfgNode extends SimpleCfgNode{
    public ForInitializerCfgNode(Expression expression){
        super(expression);
    }
}
