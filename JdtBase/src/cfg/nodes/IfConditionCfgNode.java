package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class IfConditionCfgNode extends ConditionCfgNode {
    public IfConditionCfgNode(Expression condition) {
        super(condition);
    }
}
