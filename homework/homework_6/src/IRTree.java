import java.util.Stack;

public class IRTree {

    private TreeCell root;

    public IRTree(TreeCell root) {
        this.root = root;
        this.root.calcNumRegisters();
    }

    public void sethiUllman() {
        System.out.println("Necessary stack depth = " + this.root.getNumRegisters());
        this.generateCode(root);
    }

    public void generateCode(TreeCell node) {
        TreeCell leftChild = node.getLeft();
        TreeCell rigthChild = node.getRight();

        // if node is leaf
        if (leftChild == null && rigthChild == null) {
            System.out.println("iload" + " " + node.getValuePosition());
            return;
        }

        // if node has left children
        if (leftChild != null) {
            // if node has no right children (store node)
            if (rigthChild == null) {
                this.generateCode(leftChild);
                System.out.println("istore " + node.getValuePosition());
            }
            // node has left and right children
            else {
                if (leftChild.getNumRegisters() >= rigthChild.getNumRegisters()) {
                    this.generateCode(leftChild);
                    this.generateCode(rigthChild);
                }
                else {
                    this.generateCode(rigthChild);
                    this.generateCode(leftChild);
                }
                System.out.println(node.getOp());
            }
        }

    }
}
