/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

/**
 *
 * @author PC
 */
public class AJ_ArbolDemo {
    private class TreeNode{
        String text;
        TreeNode left;
        TreeNode right;

        public TreeNode(String text, TreeNode left, TreeNode right) {
            this.text = text;
            this.left = left;
            this.right = right;
        }

        public TreeNode() {
        }

        public TreeNode(String text) {
            this.text = text;
        }
    }
    
    private TreeNode createTreeNode(){
        TreeNode res = new TreeNode("objeto");
        res.left = new TreeNode("Vivo");
        
        res.left.left = new TreeNode("Vertebrado");
        res.left.left.left = new TreeNode("Perro");
        res.left.left.right = new TreeNode("Caballo");
        
        res.left.right = new TreeNode("Invertebrado");
        res.left.right.left = new TreeNode("Gusano");
        res.left.right.right = new TreeNode("Medusa");
        
        res.right = new TreeNode("No Vivo");
        
        res.right.left = new TreeNode("Natural");
        res.right.left.left = new TreeNode("Roca");
        res.right.left.right = new TreeNode("Lago");
        
        res.right.right = new TreeNode("Artificial");
        res.right.right.left = new TreeNode("Casa");
        res.right.right.right = new TreeNode("Bicicleta");
        return res;
    }
    
    private TreeNode createTreeNodev2(){
        TreeNode res = new TreeNode("Objeto", 
                new TreeNode("Vivo", 
                    new TreeNode("Vertebrados", 
                        new TreeNode("Perro"), 
                        new TreeNode("Caballo")),
                    new TreeNode("Invertebrados", 
                        new TreeNode("Gusano"), 
                        new TreeNode("Medusa"))), 
                
                new TreeNode("No vivo", 
                    new TreeNode("Natural", 
                        new TreeNode("Roca"), 
                        new TreeNode("Lago")),
                    new TreeNode("Artificial", 
                        new TreeNode("Casa"), 
                        new TreeNode("Bicicleta"))));
        
        return res;
    }
    
    // Imprime un numero de espacios de acuerdo a un nivel de iteracion
    private void treeNodeFormat(TreeNode node, int level){
        if(node == null){
            return;
        }
        
        for(int n = 0; n < level; n++){
            if(n < (level -1)){
                System.out.print(" | "); // 4 espacios
            }else{
                System.out.print(" +- "); // 4 espacios
            }
        }
        
        System.out.println(node.text);
        // imprimimos 4 espacios y luego el nombre del nodo
        treeNodeFormat(node.left, level + 1);
        treeNodeFormat(node.right, level + 1);
    }
    
    private void print(TreeNode node){
        treeNodeFormat(node, 0);
    }
    
    public static void main(String[] args) {
        AJ_ArbolDemo td = new AJ_ArbolDemo();
        TreeNode root = td.createTreeNodev2();
        
        td.print(root);
    }
}
