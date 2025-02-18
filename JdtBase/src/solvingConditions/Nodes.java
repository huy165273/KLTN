package solvingConditions;

public class Nodes {
    private String data;
    private Nodes left;
    private Nodes right;

    public Nodes(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Nodes getLeft() {
        return left;
    }

    public void setLeft(Nodes left) {
        this.left = left;
    }

    public Nodes getRight() {
        return right;
    }

    public void setRight(Nodes right) {
        this.right = right;
    }

    @Override
    public String toString() {
        if (left == null && right == null) {
            return data + "";
        }
        return "(" + left + " " + data + " " + right + ")";
    }
}
