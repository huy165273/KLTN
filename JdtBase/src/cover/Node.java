package cover;

public class Node {
    private double id;
    private String content;
    private int lineInFunction;
    private int offset;
    private boolean nodeCovered;

    public Node(double id, int lineInFunction, int offset, String content){
        this.id = id;
        this.lineInFunction = lineInFunction;
        this.offset = offset;
        this.content = content;
        this.nodeCovered = false;
    }

    public Node(double id, String content){
        this.id = id;
        this.content = content;
        this.nodeCovered = false;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLineInFunction(int lineInFunction) {
        this.lineInFunction = lineInFunction;
    }

    public int getLineInFunction() {
        return lineInFunction;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isNodeCovered() {
        return nodeCovered;
    }

    public void setNodeCovered(boolean nodeCovered) {
        this.nodeCovered = nodeCovered;
    }
}

