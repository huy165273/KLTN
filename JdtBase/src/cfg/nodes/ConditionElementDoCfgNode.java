package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ConditionElementDoCfgNode extends DoConditionCfgNode{
    public ConditionElementDoCfgNode(Expression condition) {
        super(condition);
    }
}
