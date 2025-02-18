package symbolicExecution.expressionSymbolicExecution;

import org.eclipse.jdt.core.dom.*;
import symbolicExecution.Variable;

import java.util.List;

public class ArrayAccessSymbolic extends ExpressionSymbolic{
    private ArrayAccess arrayAccess;
    private Expression array;
    private Expression index;
    public ArrayAccessSymbolic (ArrayAccess arrayAccess, List<Variable> variables){
        super(arrayAccess, variables);
        this.arrayAccess = arrayAccess;
        array = arrayAccess.getArray();
        index = arrayAccess.getIndex();
    }


    // chú ý nếu variables không đọc cập nhật thì cập nhật lại
    @Override
    public Expression symbolicExecutionExpression() {
        if(array instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) array, variables);
            array = arrayAccessSymbolic.symbolicExecutionExpression();
        }

        if(index instanceof ArrayAccess){
            ArrayAccessSymbolic arrayAccessSymbolic = new ArrayAccessSymbolic((ArrayAccess) index, variables);
            index = arrayAccessSymbolic.symbolicExecutionExpression();
        } else if (index instanceof InfixExpression) {
            InfixExpressionSymbolic infixExpressionSymbolic = new InfixExpressionSymbolic((InfixExpression) index, variables);
            index = infixExpressionSymbolic.symbolicExecutionExpression();
        } else if (index instanceof Name) {
            VariableSymbolic variableSymbolic = new VariableSymbolic((Name) index, variables);
            index = variableSymbolic.symbolicExecutionExpression();
        }

        if(index instanceof NumberLiteral){
            int indexInt = (int) Double.parseDouble(((NumberLiteral) index).getToken());
            Variable variable = findArrayAccessInVariable(indexInt, array);
            if(variable != null){
                return variable.getValue();
            }
        } else {
            return ArrayAccessSymbolic.createArrayAccess(array, index);
        }
        return null;
    }

    public Variable findArrayAccessInVariable(int index, Expression array){
        for(Variable variable : variables){
            if(variable.getName().toString().equals(array.toString())){
                ArrayType arrayType = (ArrayType) variable.getType();
                setType(arrayType.getElementType());
                if(variable.getIndex() == index ){
                    return variable;
                }
            }
        }
        return null;
    }

    public static ArrayAccess createArrayAccess( Expression array, Expression index){
        AST ast = AST.newAST(AST.JLS8);
        ArrayAccess arrayAccess1 = ast.newArrayAccess();
        Expression array1 = (Expression) ASTNode.copySubtree(ast, array);
        Expression index1 = (Expression) ASTNode.copySubtree(ast, index);
        arrayAccess1.setIndex(index1);
        arrayAccess1.setArray(array1);
        return arrayAccess1;
    }

    public Expression getArray() {
        return array;
    }

    public void setArray(Expression array) {
        this.array = array;
        AST ast = AST.newAST(AST.JLS8);
        Expression arrayCopy = (Expression) ASTNode.copySubtree(ast, array);
        arrayAccess.setIndex(arrayCopy);
    }

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = index;
        AST ast = AST.newAST(AST.JLS8);
        Expression indexCopy = (Expression) ASTNode.copySubtree(ast, index);
        arrayAccess.setIndex(indexCopy);
    }

    public ArrayAccess getArrayAccess() {
        return arrayAccess;
    }

    public void setArrayAccess(ArrayAccess arrayAccess) {
        this.arrayAccess = arrayAccess;
    }
}
