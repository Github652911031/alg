package com.zy.tree;

/**
 * 判断一棵树是否为判断一个树是否是平衡二叉树
 * 平衡二叉树的定义：当二叉树的任意一棵子树的左子树的高度和右子树的高度相差不超过1时，该二叉树为平衡二叉树。
 * 根据定义可知，要确认一个二叉树是否是平衡二叉树势必要遍历所有结点。而遍历到每个结点时，要想知道以该结点为根结点的子树是否是平衡二叉树，我们要收集两个信息：

 该结点的左子树、右子树是否是平衡二叉树
 左右子树的高度分别是多少，相差是否超过1
 那么我们来到某个结点时（子过程），我们需要向上层（父过程）
 返回的信息就是该结点为根结点的树是否是平衡二叉树以及该结点的高度，这样的话，父过程就能继续向上层返回应该收集的信息。
 */
public class IsbalanceTree {

    public static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }

    /**
     * 遍历时，来到某个结点需要收集的信息
     * 1、以该结点为根节点的树是否是平衡二叉树
     * 2、该结点的高度
     */
    public static class ReturnData{
        public boolean isBalanced;
        public int height;
        public ReturnData(boolean isBalanced, int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }


    }

    public static ReturnData isBalancedBinaryTree(Node node){
        if(node == null){
            return new ReturnData(true, 0);
        }
        ReturnData left = isBalancedBinaryTree(node.left);
        ReturnData right = isBalancedBinaryTree(node.right);
        if(!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1){
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(left.height, right.height) + 1);
    }
}
