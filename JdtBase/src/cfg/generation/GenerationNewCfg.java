package cfg.generation;

import cfg.CFG;
import cfg.ICFG;
import cfg.nodes.*;

import java.util.ArrayList;

public class GenerationNewCfg {
    private ICFG _cfg;
    private int maxIterationsLoop2 = 2;


    public GenerationNewCfg(ICFG cfg) {
        this._cfg = cfg;
    }

    public ICFG generateCFG() {

        ICfgNode beginNode = this._cfg.getBeginNode();
        ArrayList<ICfgNode> allNode = this._cfg.getAllNodes();
        ArrayList<ICfgNode> statementList = new ArrayList<ICfgNode>();
        this.GenerateGraphFromCfg(beginNode, statementList);
        CFG result = new CFG(statementList);
        result.setRoot(beginNode);
        return result;
    }

    public void checkNodeGraph(ICfgNode stmt, ArrayList<ICfgNode> cfg) {
        ICfgNode trueNode = stmt.getTrueNode();
        ICfgNode falseNode = stmt.getFalseNode();

        if (FlagCfgNode.isEndNode(stmt)) {
        } else if (trueNode == falseNode) {
            cfg.add(stmt);
            this.checkNodeGraph(trueNode, cfg);
        } else {
            if (stmt instanceof ForConditionCfgNode) {
                maxIterationsLoop2--;

                if (maxIterationsLoop2 < 0) {
                    ICfgNode getParent = stmt.getParent();
                    ForConditionCfgNode newNode = new ForConditionCfgNode(((ForConditionCfgNode) stmt).getAstCondition());
                    getParent.setBranch(newNode);

                    newNode.setContent(stmt.getContent());
                    newNode.setBranch(falseNode);
                    cfg.add(newNode);
                    this.checkNodeGraph(falseNode, cfg);
                } else {
                    ICfgNode getParent = stmt.getParent();
                    ForConditionCfgNode newNode = new ForConditionCfgNode(((ForConditionCfgNode) stmt).getAstCondition());
                    getParent.setBranch(newNode);
                    newNode.setContent(stmt.getContent());
                    newNode.setBranch(trueNode);
                    cfg.add(newNode);
                    this.checkNodeGraph(trueNode, cfg);
                }
            } else {
                cfg.add(stmt);
                this.checkNodeGraph(trueNode, cfg);
            }
        }
    }


    public void GenerateGraphFromCfg(ICfgNode stmt, ArrayList<ICfgNode> cfg) {

        if(FlagCfgNode.isEndNode(stmt) ) {
            ICfgNode NewNode = this.NodeCopy(stmt);
            cfg.add(NewNode);
        } else {
            ICfgNode trueNode = stmt.getTrueNode();
            ICfgNode falseNode = stmt.getFalseNode();
            if (trueNode != falseNode) {
                //Câu lệnh điều kiện
                if(stmt instanceof ForConditionCfgNode) {
                    maxIterationsLoop2--;
                    if (maxIterationsLoop2 < 0) {
                        ICfgNode NewNode = this.NodeCopy(stmt);
                        cfg.add(NewNode);
                        ICfgNode NewFalseNode = this.NodeCopy(falseNode);
                        this.GenerateGraphFromCfg(NewFalseNode,cfg);
                    } else {
                        ICfgNode NewNode = this.NodeCopy(stmt);
                        cfg.add(NewNode);
                        ICfgNode NewTrueNode = this.NodeCopy(trueNode);
                        this.GenerateGraphFromCfg(NewTrueNode,cfg);
                    }

                } else if (stmt instanceof IfConditionCfgNode) {
                    ICfgNode NewNode = this.NodeCopy(stmt);
                    cfg.add(NewNode);
                    ICfgNode NewTrueNode = this.NodeCopy(trueNode);
                    this.GenerateGraphFromCfg(NewTrueNode,cfg);
                }

            } else if (trueNode == falseNode) {
                //Câu lệnh tuần tự
                ICfgNode NewNode = this.NodeCopy(stmt);
                cfg.add(NewNode);
                ICfgNode NewTrueNode = this.NodeCopy(trueNode);
                this.GenerateGraphFromCfg(NewTrueNode,cfg);
            }
        }
    }

    public ICfgNode NodeCopy(ICfgNode stmt) {
        ICfgNode x = new CfgNode();



        if (stmt instanceof ForConditionCfgNode) {
             x = new ForConditionCfgNode(((ForConditionCfgNode) stmt).getAstCondition());
            x.setTrueNode(stmt.getTrueNode());
            x.setFalseNode(stmt.getFalseNode());
            x.setContent(stmt.getContent());
        }else {
            x.setTrueNode(stmt.getTrueNode());
            x.setFalseNode(stmt.getFalseNode());
            x.setContent(stmt.getContent());
        }
        return x;


    }
}
