package cfg.generation;

import cfg.CFG;
import cfg.ICFG;
import cfg.Utils;
import cfg.nodes.*;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;

import java.util.ArrayList;
import java.util.List;


/**
 * Generate control flow graph from source code for sub-condition coverage
 *
 * @author DucAnh
 */

public class CFGGenerationSubCondition {

    private ICFG cfg;
    /**
     * Type of For block analysis
     */
    private int forModel = ICFGGeneration.DONOT_SEPARATE_FOR;

    public CFGGenerationSubCondition(ICFG cfg, int forModel) {
        this.cfg = cfg;
        this.forModel = forModel;
    }

    public ICFG generateCFG()  {
        return parse(cfg);
    }

    private ICFG parse(ICFG cfg)  {
        ICFG cfgSubcondition = new CFG(cfg);
//        cfg.setIdforAllNodes();
        boolean containComplexConditions = true;
        while (containComplexConditions) {
            containComplexConditions = false;

            for (ICfgNode cfgNode : cfgSubcondition.getAllNodes())
                if (cfgNode instanceof ConditionCfgNode && isComplexCondition((ConditionCfgNode) cfgNode)) {
                    createGraphForComplexCondition((ConditionCfgNode) cfgNode, cfgSubcondition);
                    containComplexConditions = true;
                    break;
                }
        }
        return cfgSubcondition;
    }

    private void createGraphForComplexCondition(ConditionCfgNode complexConditionNode, ICFG cfg) {
        Expression a = complexConditionNode.getAstCondition();
        if (a instanceof InfixExpression) {
            Expression left = ((InfixExpression) a).getLeftOperand();
            Expression right = ((InfixExpression) a).getRightOperand();
            List extended = ((InfixExpression) a).extendedOperands();

            ConditionCfgNode leftNode = null;
            ConditionCfgNode rightNode = null;
            List<ConditionCfgNode> extendedNodes = new ArrayList<>();
            InfixExpression.Operator x = ((InfixExpression) a).getOperator();
            if (x.equals(InfixExpression.Operator.CONDITIONAL_AND) || x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                if (complexConditionNode instanceof DoConditionCfgNode
                        || complexConditionNode instanceof ConditionElementDoCfgNode) {
                    leftNode = new ConditionElementDoCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementDoCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                    for (Object condition : extended){
                        ConditionCfgNode node = new ConditionElementDoCfgNode(Utils.shortenASTCondition((Expression) condition));
                        node.setContent(Utils.shortenASTCondition((Expression) condition).toString());
                        extendedNodes.add(node);
                    }
                } else if (complexConditionNode instanceof IfConditionCfgNode
                        || complexConditionNode instanceof ConditionElementIfCfgNode) {
                    leftNode = new ConditionElementIfCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementIfCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                    for (Object condition : extended){
                        ConditionCfgNode node = new ConditionElementIfCfgNode(Utils.shortenASTCondition((Expression) condition));
                        node.setContent(Utils.shortenASTCondition((Expression) condition).toString());
                        extendedNodes.add(node);
                    }
                } else if (complexConditionNode instanceof ForConditionCfgNode
                        || complexConditionNode instanceof ConditionElementForCfgNode) {
                    leftNode = new ConditionElementForCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementForCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                    for (Object condition : extended){
                        ConditionCfgNode node = new ConditionElementForCfgNode(Utils.shortenASTCondition((Expression) condition));
                        node.setContent(Utils.shortenASTCondition((Expression) condition).toString());
                        extendedNodes.add(node);
                    }
                } else if (complexConditionNode instanceof WhileConditionCfgNode
                        || complexConditionNode instanceof ConditionElementWhileCfgNode) {
                    leftNode = new ConditionElementWhileCfgNode(Utils.shortenASTCondition(left));
                    leftNode.setContent(Utils.shortenASTCondition(left).toString());
                    rightNode = new ConditionElementWhileCfgNode(Utils.shortenASTCondition(right));
                    rightNode.setContent(Utils.shortenASTCondition(right).toString());
                    for (Object condition : extended){
                        ConditionCfgNode node = new ConditionElementWhileCfgNode(Utils.shortenASTCondition((Expression) condition));
                        node.setContent(Utils.shortenASTCondition((Expression) condition).toString());
                        extendedNodes.add(node);
                    }
                }
            }

            if (leftNode != null && rightNode != null) {
                ICfgNode parent = complexConditionNode.getParent();
                ICfgNode falseBranch = complexConditionNode.getFalseNode();
                ICfgNode trueBranch = complexConditionNode.getTrueNode();
                if (x.equals(InfixExpression.Operator.CONDITIONAL_AND)) {
                    leftNode.setParent(parent);
                    leftNode.setTrueNode(rightNode);
                    leftNode.setFalseNode(falseBranch);
                    rightNode.setParent(leftNode);
                    rightNode.setFalseNode(falseBranch);
                    if(extendedNodes.isEmpty()){
                        rightNode.setTrueNode(trueBranch);
                    } else {
                        rightNode.setTrueNode(extendedNodes.get(0));
                        for (int i = 0; i < extendedNodes.size(); i ++){
                            if(i == 0){
                                extendedNodes.get(0).setParent(rightNode);
                            } else {
                                extendedNodes.get(i).setParent(extendedNodes.get(i - 1));
                            }
                            extendedNodes.get(i).setFalseNode(falseBranch);
                            if (extendedNodes.size() > i + 1){
                                extendedNodes.get(i).setTrueNode(extendedNodes.get(i + 1));
                            }  else {
                                extendedNodes.get(i).setTrueNode(trueBranch);
                            }
                        }
                    }
                } else if (x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                    leftNode.setParent(parent);
                    leftNode.setTrueNode(trueBranch);
                    leftNode.setFalseNode(rightNode);
                    rightNode.setParent(leftNode);
                    rightNode.setTrueNode(trueBranch);
                    if(extendedNodes.isEmpty()){
                        rightNode.setFalseNode(falseBranch);
                    } else {
                        rightNode.setFalseNode(extendedNodes.get(0));
                        for (int i = 0; i < extendedNodes.size(); i ++){
                            if(i == 0){
                                extendedNodes.get(0).setParent(rightNode);
                            } else {
                                extendedNodes.get(i).setParent(extendedNodes.get(i - 1));
                            }
                            extendedNodes.get(i).setTrueNode(trueBranch);
                            if (extendedNodes.size() > i + 1){
                                extendedNodes.get(i).setFalseNode(extendedNodes.get(i + 1));
                            }  else {
                                extendedNodes.get(i).setFalseNode(falseBranch);
                            }
                        }
                    }
                }

                // Update all nodes
                leftNode.setParentCondition(complexConditionNode);
                rightNode.setParentCondition(complexConditionNode);

                int index = cfg.getAllNodes().indexOf(complexConditionNode);
                cfg.getAllNodes().add(index, leftNode);
                cfg.getAllNodes().add(index + 1,rightNode);
                for (int i = 0; i < extendedNodes.size(); i++){
                    extendedNodes.get(i).setParentCondition(complexConditionNode);
                    cfg.getAllNodes().add(index + 2 + i, extendedNodes.get(i));
                }
                cfg.getAllNodes().remove(complexConditionNode);

                for (ICfgNode node : cfg.getAllNodes())
                    if (node instanceof ScopeCfgNode) {
                        ScopeCfgNode castNode = (ScopeCfgNode) node;
                        if (castNode.getFalseNode().equals(complexConditionNode)
                                || castNode.getTrueNode().equals(complexConditionNode))
                            ((ScopeCfgNode) node).setBranch(leftNode);

                    } else if (node instanceof NormalCfgNode) {

                        if (((NormalCfgNode) node).isCondition()) {
                            ConditionCfgNode castNode = (ConditionCfgNode) node;

//                            if(castNode.getTrueNode() == null){
//                                System.out.println(castNode);
//                            }
//                            if (castNode.getTrueNode() == null || complexConditionNode == null){
//                                System.out.println(castNode.getTrueNode());
//                                System.out.println(complexConditionNode);
//                                System.out.println(castNode);
//                            }
                            if (castNode.getTrueNode().equals(complexConditionNode))
                                castNode.setTrueNode(leftNode);
                            else if (castNode.getFalseNode().equals(complexConditionNode))
                                castNode.setFalseNode(leftNode);

                        } else if (node instanceof SimpleCfgNode) {
                            SimpleCfgNode castNode = (SimpleCfgNode) node;
                            if (castNode.getFalseNode().equals(complexConditionNode)
                                    || castNode.getTrueNode().equals(complexConditionNode))
                                ((SimpleCfgNode) node).setBranch(leftNode);
                        }
                    }
            }


        }

    }

    private boolean isComplexCondition(ConditionCfgNode cfgNode) {
        boolean isComplexCon = false;

        ASTNode ast = cfgNode.getAstCondition();
        Expression a = cfgNode.getAstCondition();
        if (ast != null) {
            if (a instanceof InfixExpression) {
                InfixExpression.Operator x = ((InfixExpression) a).getOperator();
                if (x.equals(InfixExpression.Operator.CONDITIONAL_AND) || x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                    isComplexCon = true;
                }
            }
        }
        return isComplexCon;
    }

    private boolean isComplexCondition(Expression condition) {
        if (condition instanceof InfixExpression) {
            InfixExpression.Operator x = ((InfixExpression) condition).getOperator();
            if (x.equals(InfixExpression.Operator.CONDITIONAL_AND) || x.equals(InfixExpression.Operator.CONDITIONAL_OR)) {
                return true;
            }
        }
        return false;
    }



    public ICFG getFunctionNode() {
        return cfg;
    }

    public void setFunctionNode(ICFG cfg) {
        this.cfg = cfg;
    }
}
