package cfg.nodes;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;

public class SimpleCfgNode extends NormalCfgNode {
    private Statement _statement;
    private Expression expression;
    private SingleVariableDeclaration variableDeclaration;

    public SimpleCfgNode (Statement statement) {
        super();
        this._statement = statement;
    }

    public SimpleCfgNode(Expression iterAst) {
        super();
        this.expression = iterAst;
    }

    public SimpleCfgNode(SingleVariableDeclaration variableDeclaration) {
        this.variableDeclaration = variableDeclaration;
    }

    public Statement getStatement() {
        return this._statement;
    }

    public void setStatement (Statement value ){
        this._statement = value;
    }

    public SingleVariableDeclaration getVariableDeclaration() {
        return variableDeclaration;
    }

    public void setVariableDeclaration(SingleVariableDeclaration variableDeclaration) {
        this.variableDeclaration = variableDeclaration;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
