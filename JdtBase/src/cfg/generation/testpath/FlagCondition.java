package cfg.generation.testpath;

import cfg.nodes.CaseCfgNode;
import cfg.nodes.ConditionCfgNode;
import cfg.nodes.SimpleCfgNode;


public class FlagCondition {
    private ConditionCfgNode _cfgNode;
    private SimpleCfgNode _cfgNode2;
    private Direction _flag;
    // public static readonly TRUE: number = 1;
    // public static readonly FALSE: number = 0;


    public FlagCondition(ConditionCfgNode cfgNode,Direction flag) {
        this._cfgNode = cfgNode;
        this._flag = flag;
    }

    public FlagCondition(CaseCfgNode stmt, Direction direction) {
        this._cfgNode2 = stmt;
        this._flag = direction;
    }

    public ConditionCfgNode getCfgNode() {
        return this._cfgNode;
    }

    public void setCfgNode(ConditionCfgNode value) {
        this._cfgNode = value;
    }


    public Direction getFlag() {
        return this._flag;
    }

    public void setFlag(Direction value) {
        this._flag = value;
    }

    public String toString(){
        return this._flag.toString();
    }
}
