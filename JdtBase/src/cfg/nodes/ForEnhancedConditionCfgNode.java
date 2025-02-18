package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ForEnhancedConditionCfgNode extends LoopConditionCfgNode{
    public ForEnhancedConditionCfgNode(Expression condition) {
        super(condition);
    }
}
