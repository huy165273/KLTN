package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;

public class SwitchCfgNode extends ConditionCfgNode{
    public SwitchCfgNode(Expression condition) {
        super(condition);
    }

}
