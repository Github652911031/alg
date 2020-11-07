package com.zy.tree;

import java.util.Stack;

/**
 * 二叉树遍历算法
 * 递归和非递归实现
 */
public class BinaryTreeTraversal {

    static class  Node{
        int val;
        Node left = null;
        Node right = null;

        public Node(int val){
            this.val = val;
        }
        public Node(int val, Node left, Node right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //前序遍历递归实现
    public static void preOrderRecursion(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    //前序遍历非递归实现
    public static void preOrder(Node root){
        Stack<Node> s = new Stack<>();
        while(root != null || !s.isEmpty()){
            if(root != null){
                System.out.println(root.val);
                s.push(root);
                root = root.left;
            }else{
                root = s.peek(); //回溯至父亲节点
                root = root.right;
                s.pop();
            }

        }

    }

    //中序遍历递归实现
    public static void inOrderRecursion(Node root){
        if(root == null){
            return;
        }
        preOrderRecursion(root.left);
        System.out.println(root.val);
        preOrderRecursion(root.right);
    }

    //中序遍历非递归实现
    public static void inOrder(Node root){
        Stack<Node> s = new Stack<>();
        while(root != null || !s.isEmpty()){
            if(root != null){
                s.push(root);
                root = root.left;
            }else{
                root = s.peek();
                System.out.println(root.val);
                s.pop();
                root = root.right;
            }
        }
    }

    //后序遍历递归实现
    public static void postOrderRecursion(Node root){
        if(root == null){
            return;
        }
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
        System.out.println(root.val);
    }

    //后序遍历非递归实现

    /**
     * 后续遍历的非递归算法比较复杂，使用一个栈可以实现，但是过程很繁琐，
     * 我们可以考虑用两个栈来实现后续遍历的非递归算法。
     * 注意到后续遍历可以看作是下面遍历的逆过程：即先遍历某个结点，然后遍历其右孩子，然后遍历其左孩子。
     * 这个过程逆过来就是后续遍历。
     * @param root
     */
    public static void postOrder(Node root){
        Stack<Node> s = new Stack<>();
        Stack<Node> out = new Stack<>();
        if(root == null) return;
        s.push(root);
        while(!s.isEmpty()){
            Node node = s.pop();
            out.push(node);   //个人理解：用到两个栈，其中一个栈负责遍历，另一个栈负责存储
                             //循环结束条件根据栈是否为空判断
            if(node.left != null){
                s.push(node.left);
            }
            if(node.right != null){
                s.push(node.right);
            }
        }

        while(!out.isEmpty()){
            System.out.println(out.pop().val);
        }

    }


    public static void main(String[] args){
        int[] val = {1,2,3,4,6,7,8,9};
        int index = 0;
        Node root = new Node(val[0]);
        createTree(root,index,val);
        preOrderRecursion(root);

    }

    /**
     * 构造一棵二叉树
     * @return
     */
    private static void createTree(Node node,int i,int[] arr) {
        int Lindex = 2 * i + 1;
        int Rindex = 2 * i + 2;
        if(Lindex < arr.length){
            Node left = new Node(arr[Lindex]);
            node.left = left;
            createTree(left,Lindex,arr);
        }
        if(Rindex < arr.length){
            Node right = new Node(arr[Rindex]);
            node.right = right;
            createTree(right,Rindex,arr);
        }

    }


}
