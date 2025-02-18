package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ConditionElementWhileCfgNode extends WhileConditionCfgNode{
    public ConditionElementWhileCfgNode(Expression condition) {
        super(condition);
    }
}
