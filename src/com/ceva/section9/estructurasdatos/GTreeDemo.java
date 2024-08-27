package com.ceva.section9.estructurasdatos;

public class GTreeDemo {
    private class TreeNode {
        String text;
        TreeNode left;
        TreeNode right;

        TreeNode() { }

        TreeNode(String text) {
            this.text = text;
        }

        TreeNode(String text, TreeNode left, TreeNode right) {
            this.text = text;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode createTreeNode() {
        TreeNode res = new TreeNode("Objeto");
        res.left = new TreeNode("Vivo");
        res.left.left = new TreeNode("Vertebrado");
        res.left.left.left = new TreeNode("Perro");
        res.left.left.right = new TreeNode("Caballo");
        res.left.right = new TreeNode("Invertebrado");
        res.left.right.left = new TreeNode("Gusano");
        res.left.right.right = new TreeNode("Medusa");

        res.right = new TreeNode("No vivo");
        res.right.left = new TreeNode("Natural");
        res.right.left.left = new TreeNode("Roca");
        res.right.left.right = new TreeNode("Lago");
        res.right.right = new TreeNode("Artificial");
        res.right.right.left = new TreeNode("Casa");
        res.right.right.right = new TreeNode("Bicicleta");

        return res;
    }

    private TreeNode createTreeNode2() {
        TreeNode res = new TreeNode("Objeto",
                new TreeNode("Vivo",
                        new TreeNode("Vertebrado",
                                new TreeNode("Perro"),
                                new TreeNode("Caballo")
                        ),
                        new TreeNode("Invertebrado",
                                new TreeNode("Gusano"),
                                new TreeNode("Medusa")
                        )
                ),
                new TreeNode("No vivo",
                        new TreeNode("Natural",
                                new TreeNode("Roca"),
                                new TreeNode("Lago")
                        ),
                        new TreeNode("Artificial",
                                new TreeNode("Casa"),
                                new TreeNode("Bicicleta")
                        )
                )
        );
        return res;
    }

    private void recursivePrint(TreeNode node, int level) {
        if (node == null)
            return;
        for (int n=0; n<level; n++) {
            if (n < (level-1)) {
                System.out.print(" |  ");
            } else
                System.out.print(" +- ");
        }
        System.out.println(node.text);
        recursivePrint(node.left, level+1);
        recursivePrint(node.right, level+1);
    }

    private void print(TreeNode node) {
        recursivePrint(node, 0);
    }

    public static void main(String[] args) {
        GTreeDemo demo = new GTreeDemo();
        TreeNode root = demo.createTreeNode();

        demo.print(root);
    }
}
