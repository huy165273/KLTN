package cfg.nodes;

public interface ICfgNode {
    String getContent();
    void setContent(String content);
    double getId();
    void setId(double id);
    void setBranch(ICfgNode cfgNode);
    ICfgNode getTrueNode();
    void setTrueNode(ICfgNode cfgNode);
    ICfgNode getFalseNode();
    void setFalseNode(ICfgNode cfgNode);
    boolean isVisited();
    void setVisited(boolean visited);
    ICfgNode getParent();
    void setParent(ICfgNode parent);
    boolean isCondition();
    boolean isNormalNode();
    void resetId();
}
