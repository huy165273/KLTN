package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ForConditionCfgNode extends LoopConditionCfgNode{
    public ForConditionCfgNode(Expression condition) {
        super(condition);
    }
}
