package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ConditionElementForCfgNode extends ForConditionCfgNode{
    public ConditionElementForCfgNode(Expression condition) {
        super(condition);
    }
}
