package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class WhileConditionCfgNode extends LoopConditionCfgNode{
    public WhileConditionCfgNode(Expression condition) {
        super(condition);
    }
}
