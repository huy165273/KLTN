package cover;

public class ConditionNode extends Node {
    private boolean trueNodeCovered;
    private boolean falseNodeCovered;
    private ConditionNode parentConditionNode;

    public ConditionNode(double id, int lineInFunction, int offset, String content, ConditionNode parentConditionNode) {
        super(id, lineInFunction, offset, content);
        this.parentConditionNode = parentConditionNode;
    }

    public ConditionNode(double id, int lineInFunction, int offset, String content) {
        super(id, lineInFunction, offset, content);
    }

    public ConditionNode(double id, String content) {
        super(id, content);
    }

    public ConditionNode getParentConditionNode() {
        return parentConditionNode;
    }

    public void setParentConditionNode(ConditionNode parentConditionNode) {
        this.parentConditionNode = parentConditionNode;
    }

    public boolean isFalseNodeCovered() {
        return falseNodeCovered;
    }

    public boolean isTrueNodeCovered() {
        return trueNodeCovered;
    }

    public void setFalseNodeCovered(boolean falseNodeCovered) {
        this.falseNodeCovered = falseNodeCovered;
    }

    public void setTrueNodeCovered(boolean trueNodeCovered) {
        this.trueNodeCovered = trueNodeCovered;
    }
}

