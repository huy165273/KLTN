package mark;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class MethodRefactor {
    private MethodDeclaration node;
    private StringBuilder contentMethod;
    private int index;
    public MethodRefactor(MethodDeclaration node){
        this.node = node;
        this.contentMethod = new StringBuilder(node.toString());
        this.index = 0;
    }

    public void refactorContentMethod(){
        Block body = node.getBody();
//        System.out.println(body.toString());
        List statementList = body.statements();
        int indexStatement = contentMethod.indexOf(body.toString());
        contentMethod.replace(indexStatement, indexStatement + body.toString().length(), "");
        contentMethod.append("{\n");
        indexStatement += 2;
        for (int i = 0; i < statementList.size(); i++){
            if(statementList.get(i) instanceof Statement){
                contentMethod.replace(indexStatement, indexStatement, statementList.get(i).toString());
                indexStatement += statementList.get(i).toString().length();
            }
        }
        contentMethod.append("}");
        refactorContentBlock(body);
//        System.out.println(contentMethod);
    }

    private void refactorContentBlock(Block block){

        List statementList = block.statements();

        for (Object statement : statementList) {
//            index = contentMethod.indexOf(statement.toString(), index);
            if (statement instanceof IfStatement) {
                refactorContentIfStatement((IfStatement) statement);
            } else if (statement instanceof ForStatement) {
                refactorContentForStatement((ForStatement) statement);
            } else if (statement instanceof WhileStatement) {
                refactorContentWhileStatement((WhileStatement) statement);
            } else if (statement instanceof DoStatement) {
                refactorContentDoStatement((DoStatement) statement);
            } else if (statement instanceof EnhancedForStatement) {
                refactorContentEnhancedForStatement((EnhancedForStatement) statement);
            } else {
                index += statement.toString().length();
            }

        }
    }

    private void refactorContentStatement(Statement statement){
        index = contentMethod.indexOf(statement.toString(), index);
        if (index == -1){
            System.out.println("-1 " + statement);
            System.out.println(contentMethod);
        }
        contentMethod.replace(index, index + statement.toString().length(), "{" + statement + "}");
        if(statement instanceof IfStatement){
            refactorContentIfStatement((IfStatement) statement);
        } else if (statement instanceof ForStatement) {
            refactorContentForStatement((ForStatement) statement);
        } else if (statement instanceof WhileStatement) {
            refactorContentWhileStatement((WhileStatement) statement);
        } else if (statement instanceof DoStatement) {
            refactorContentDoStatement((DoStatement) statement);
        } else if (statement instanceof EnhancedForStatement) {
            refactorContentEnhancedForStatement((EnhancedForStatement) statement);
        } else {
            index += statement.toString().length() + 2; // 2 là độ dài của {}
        }

    }

    private void refactorContentWhileStatement(WhileStatement whileStatement){
        Statement body = whileStatement.getBody();
        if(body instanceof Block){
            refactorContentBlock((Block) body);
        } else {
            refactorContentStatement(body);
        }
    }

    private void refactorContentEnhancedForStatement(EnhancedForStatement enhancedForStatement){
        Statement body = enhancedForStatement.getBody();
        if(body instanceof Block){
            refactorContentBlock((Block) body);
        } else {
            refactorContentStatement(body);
        }
    }

    private void refactorContentDoStatement(DoStatement doStatement){
        Statement body = doStatement.getBody();
        if(body instanceof Block){
            refactorContentBlock((Block) body);
        } else {
            refactorContentStatement(body);
        }
    }

    private void refactorContentIfStatement(IfStatement ifStatement){
        Statement thenStatement = ifStatement.getThenStatement();
        Statement elseStatement = ifStatement.getElseStatement();
        if(thenStatement instanceof Block){
            refactorContentBlock((Block) thenStatement);
        } else {
            refactorContentStatement(thenStatement);
        }

        if(elseStatement != null){
            if(elseStatement instanceof Block){
                refactorContentBlock((Block) elseStatement);
            } else {
                if(elseStatement instanceof IfStatement){
                    refactorContentIfStatement((IfStatement) elseStatement);
                }else {
                    refactorContentStatement(elseStatement);
                }
            }
        }
    }

    private void refactorContentForStatement(ForStatement forStatement){
        Statement body = forStatement.getBody();
        if(body instanceof Block){
            refactorContentBlock((Block) body);
        } else {
            refactorContentStatement(body);
        }
    }

    public void setNode(MethodDeclaration node) {
        this.node = node;
    }

    public MethodDeclaration getNode() {
        return node;
    }

    public StringBuilder getContentMethod() {
        return contentMethod;
    }

    public void setContentMethod(StringBuilder contentMethod) {
        this.contentMethod = contentMethod;
    }
}
