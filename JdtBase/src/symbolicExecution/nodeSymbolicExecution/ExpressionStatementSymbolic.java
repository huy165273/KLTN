package symbolicExecution.nodeSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;
import symbolicExecution.expressionSymbolicExecution.AssignmentSymbolic;
import symbolicExecution.expressionSymbolicExecution.PostfixExpressionSymbolic;

import java.util.List;

public class ExpressionStatementSymbolic extends NodeSymbolic {
    private ExpressionStatement expressionStatement;
    public ExpressionStatementSymbolic(ExpressionStatement expressionStatement, List<Variable> variables){
        super(expressionStatement, variables);
        this.expressionStatement = expressionStatement;
    }

    @Override
    public Expression symbolicExecutionNode(int scope) {
        Expression expression = expressionStatement.getExpression();
        if (expression instanceof Assignment) {
            AssignmentSymbolic ass = new AssignmentSymbolic((Assignment) expression, variables);
            return ass.symbolicExecutionExpression();
        } else if (expression instanceof PostfixExpression ){
            PostfixExpressionSymbolic postfixExpressionSymbolic = new PostfixExpressionSymbolic((PostfixExpression) expression, variables);
            postfixExpressionSymbolic.symbolicExecutionExpression();
        }
        return null;
    }

    public ExpressionStatement getExpressionStatement() {
        return expressionStatement;
    }

    public void setExpressionStatement(ExpressionStatement expressionStatement) {
        this.expressionStatement = expressionStatement;
    }
}
