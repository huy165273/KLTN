package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;
public class ConditionCfgNode extends NormalCfgNode{
    private Expression _astCondition ;
    private ConditionCfgNode _parentConditionNode;

    public ConditionCfgNode ( Expression condition) {
        super();
        this._astCondition = condition;
    }

    public Expression getAstCondition() {
        return this._astCondition;
    }

    public void setAstCondition(Expression value) {
        this._astCondition = value;
    }
    public ConditionCfgNode getParentCondition() {
        return _parentConditionNode;
    }

    public void setParentCondition(ConditionCfgNode _parentConditionNode) {
        this._parentConditionNode = _parentConditionNode;
    }
}
