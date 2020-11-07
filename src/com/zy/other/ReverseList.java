package com.zy.other;

/**
 * 反转单链表，复杂版本
 * 给定一个单链表的头节点head，实现一个调整单链表的函数，使得每k个节点
 * 之间为一组进行逆序，并且从链表的尾部开始组起，头部剩余节点数量不够一组的
 * 不需要逆序
 */
public class ReverseList {
    /**
     * 递归反转链表
     * @return
     */
    public Node reverseList(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        //reverseList函数返回的是翻转后的头节点
        Node result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    public Node reverseKGroup(int k, Node head) {
        Node tmp = head;
        for(int i=1;i<k && tmp != null;i++) {
            tmp = tmp.next;
        }
        //判断节点数量能否凑成一组
        if(tmp == null) {
            return head;
        }
        Node t2 = tmp.next;
        tmp.next = null;
        //把当前的组进行逆序
        Node newHead = reverseList(head);
        //把之后的节点进行分组逆序
        Node newTemp = reverseKGroup(k, t2);
        //把两部分连接起来，翻转后head变为tail
        head.next = newTemp;
        return newHead;
    }


}



class Node {
    int data;
    Node next;
}

