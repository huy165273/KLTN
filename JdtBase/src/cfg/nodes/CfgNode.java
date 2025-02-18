package cfg.nodes;

public class CfgNode implements ICfgNode {
    private String _content;
    private double _id;
    private ICfgNode _parentNode;
    private ICfgNode _trueNode;
    private ICfgNode _falseNode;
    private boolean _visited;
    private static double countnode = 0;


    public CfgNode (String _content) {
        if(!_content.equals("}")){
            CfgNode.countnode++;
        }
        this._id = CfgNode.countnode;
        this._content = _content ;
    }

    public CfgNode () {
        CfgNode.countnode++;
        this._id = CfgNode.countnode;
        this._content = "" ;
    }

    @Override
    public String getContent() {
        return this._content;
    }

    @Override
    public double getId() {
        return this._id;
    }

    @Override
    public void setContent(String content) {
        this._content = content;
    }

    @Override
    public void setId(double id) {
        this._id = id;
    }

    @Override
    public void setBranch(ICfgNode cfgNode) {
        this._trueNode = cfgNode;
        this._falseNode = cfgNode;
    }

    @Override
    public ICfgNode getParent() {
        return this._parentNode;
    }

    @Override
    public void setParent(ICfgNode parent) {
        this._parentNode = parent;
    }

    @Override
    public ICfgNode getTrueNode() {
        return this._trueNode;
    }

    @Override
    public void setTrueNode(ICfgNode cfgNode) {
        this._trueNode = cfgNode;
    }

    @Override
    public ICfgNode getFalseNode() {
        return this._falseNode;
    }

    @Override
    public void setFalseNode(ICfgNode cfgNode) {
        this._falseNode = cfgNode;
    }

    @Override
    public boolean isVisited() {
        return this._visited;
    }

    @Override
    public void setVisited(boolean visited) {
        this._visited = visited;
    }

    public String toString () {
        return this._content;
    }

    public boolean isCondition() {
        return  getTrueNode() != getFalseNode();
    }

    @Override
    public boolean isNormalNode() {
        return false;
    }
//
//    public boolean isMultipleTarget() {
//        return targetNodesList != null && targetNodesList.size() >= 1;
//    }


    @Override
    public void resetId() {
        CfgNode.countnode++;
        this._id = CfgNode.countnode;
    }
}
