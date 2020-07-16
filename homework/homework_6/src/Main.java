public class Main {
    public static void main(String[] args) {
        TreeCell root = new TreeCell(4);
        TreeCell left1 = new TreeCell("+");
        TreeCell left2 = new TreeCell("+");
        TreeCell right2 = new TreeCell(3);
        TreeCell left3 = new TreeCell("*");
        TreeCell right3 = new TreeCell("*");
        TreeCell left4 = new TreeCell("*");
        TreeCell right4 = new TreeCell(1);
        TreeCell left5 = new TreeCell(0);
        TreeCell right5 = new TreeCell(1);
        TreeCell left6 = new TreeCell(2);
        TreeCell right6 = new TreeCell(1);

        root.setLeft(left1);
        left1.setLeft(left2);
        left1.setRight(right2);
        left2.setLeft(left3);
        left2.setRight(right3);
        left3.setLeft(left4);
        left3.setRight(right4);
        left4.setLeft(left5);
        left4.setRight(right5);
        right3.setLeft(left6);
        right3.setRight(right6);

        IRTree tree = new IRTree(root);
        tree.sethiUllman();
    }
}
