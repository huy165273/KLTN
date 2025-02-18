package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class ConditionElementIfCfgNode extends IfConditionCfgNode {
    public ConditionElementIfCfgNode(Expression condition) {
        super(condition);
    }
}
