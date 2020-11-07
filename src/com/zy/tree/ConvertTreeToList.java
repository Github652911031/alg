package com.zy.tree;

import java.util.List;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *      10
 *     |   |
 *     6   14              ---->
 *                                 4 <--> 6 <--> 8 <--> 10 <--> 12 <--> 14 <-->16
 *    | |  | |
 *    4 8  12 16
 */
public class ConvertTreeToList {

    public static void main(String[] args) {
        Node root = new Node(10);
        Node result = convert(root);
    }

    private static Node convert(Node root) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null){
             return root;
        }
        Node left = convert(root.left);
        Node p = left;
        while(p != null && p.right != null) {
            p = p.right;
        }
        if(p != null) {
            p.right = root;
            root.left = p;
        }
        Node right = convert(root.right);

        if(right != null) {
            right.left = root;
            root.right = right;
        }
        return left == null ? root : left;

    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;

    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
