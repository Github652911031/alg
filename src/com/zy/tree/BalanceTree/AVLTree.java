package com.zy.tree.BalanceTree;

/**
 * 平衡二叉树
 * 找到失衡节点，分为LL, LR, RR, Rl四种类型
 * LL:插入节点位于失衡节点左子树的左子树
 *
 */
public class AVLTree {
    //计算节点高度
    static int height(Node T){
        if(T == null) {
            return -1;
        }
        return T.height;
    }

    //左左型
    static Node LL(Node T) {
        Node k1 ;
        //进行旋转
        k1 = T.left;
        T.left = k1.right;
        k1.right = T;

        //重新计算节点高度
        T.height = Math.max(height(T.left), height(T.right)) + 1;
        k1.height = Math.max(height(k1.left), height(T.right)) + 1;
        return k1;
    }

    static Node RR(Node T) {
        Node k1;

        //进行旋转
        k1 = T.right;
        T.right = k1.left;
        k1.left = T;

        //重新计算节点高度
        T.height = Math.max(height(T.left), height(T.right)) + 1;
        k1.height = Math.max(height(k1.left), height((k1.right) )) + 1;
        return k1;
    }

    static Node LR(Node T) {

        //先进行RR操作，即先将底下两个节点换个位置
        /**
         *     3                        3
         *    /                        /
         *   1             ->        2
         *   \                      /
         *    2                    1
         */
        T.left = RR(T.left);
        return LL(T);
    }

    static Node RL(Node T) {
        T.right = LL(T.right);
        return RR(T);
    }

    static Node insert(int data, Node T) {
        if(T == null) {
            T = new Node();
            T.key = data;
            T.left = T.right = null;
        }else if(data < T.key){
            T.left = insert(data, T.left);
            if(height(T.left) - height(T.right) == 2) {
                if(data < T.left.key) {
                    T = LL(T);
                }else{
                    T = LR(T);
                }
            }
        }else if(data > T.key) {
            T.right = insert(data, T.right);
            if(height(T.right) - height(T.left) == 2) {
                if(data < T.right.key) {
                    T = RL(T);
                }else {
                    T = RR(T);
                }
            }
        }
        //否则，这个节点已经在树上存在了，我们什么都不做
        //重新计算T的高度
        T.height = Math.max(height(T.left), height(T.right)) + 1;
        return T;
    }
}
