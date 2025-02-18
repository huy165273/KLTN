package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class DoConditionCfgNode extends LoopConditionCfgNode{
    public DoConditionCfgNode(Expression condition) {
        super(condition);
    }
}
