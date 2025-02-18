package cfg;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;


public class Utils {

    public static Expression shortenASTCondition(Expression expression) {
        Expression tmp  = expression;
        if (tmp instanceof ParenthesizedExpression) {
            tmp = shortenParenthesizedExpression(tmp);
        }
        if (tmp instanceof InfixExpression) {
            // console.log("Binary expression", tmp.getText());
            Expression a = ((InfixExpression) tmp).getLeftOperand();
            Expression b = ((InfixExpression) tmp).getRightOperand();
            InfixExpression.Operator x = ((InfixExpression) tmp).getOperator();

            return tmp;
        } else if (tmp instanceof PrefixExpression) {
//            console.log("Prefix Unary Expression", tmp.getText());
            System.out.println("Prefix Unary Expression" + tmp.toString());
            return tmp;
        }
        return tmp;
    }

    public static Expression shortenParenthesizedExpression(Expression expression) {
        while (expression instanceof ParenthesizedExpression) {
            ParenthesizedExpression tmp  = (ParenthesizedExpression) expression;
            expression = tmp.getExpression();
        }
        return expression;
    }
}
