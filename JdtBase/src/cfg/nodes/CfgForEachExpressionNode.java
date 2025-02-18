package cfg.nodes;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Statement;

public class CfgForEachExpressionNode extends NormalCfgNode{
    private Expression _astCondition ;
    public CfgForEachExpressionNode(Statement statement,Expression condition) {

        this._astCondition = condition;
    }

//    public CfgForEachExpressionNode(SingleVariableDeclaration variableDeclaration, Expression condition) {
//        super();
//    }
}
