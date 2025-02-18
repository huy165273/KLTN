package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class LoopConditionCfgNode extends ConditionCfgNode{
    public LoopConditionCfgNode(Expression condition) {
        super(condition);
    }
}
