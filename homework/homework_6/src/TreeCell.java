public class TreeCell {
    private TreeCell left;
    private TreeCell right;
    private String op;
    private int valuePosition;
    private int numRegisters;

    public TreeCell(String op) {
        this.op = op;
    }

    public TreeCell(int valuePosition) {
        this.left = null;
        this.right = null;
        this.valuePosition = valuePosition;
    }

    public void setLeft(TreeCell left) {
        this.left = left;
    }

    public void setRight(TreeCell right) {
        this.right = right;
    }

    public TreeCell getLeft() {
        return left;
    }

    public TreeCell getRight() {
        return right;
    }

    public String getOp() {
        switch (this.op) {
            case "+":
                return "iadd";
            case "*":
                return "imul";
            default:
                return "";
        }
    }

    public int getValuePosition() {
        return valuePosition;
    }

    public int getNumRegisters() {
        return numRegisters;
    }

    public int calcNumRegisters() {
        // if node is leaf
        if (this.left == null && this.right == null) {
            this.numRegisters = 1;
            return this.numRegisters;
        }
        // if node has left and right children
        int leftRegisters = 0, rightRegisters = 0;
        if (this.left != null) {
            leftRegisters = this.left.calcNumRegisters();
        }
        if (this.right != null) {
            rightRegisters = this.right.calcNumRegisters();
        }

        if (leftRegisters == rightRegisters) {
            this.numRegisters = leftRegisters + 1;
            return this.numRegisters;
        }
        else {
            this.numRegisters = Math.max(leftRegisters, rightRegisters);
            return this.numRegisters;
        }
    }

}
