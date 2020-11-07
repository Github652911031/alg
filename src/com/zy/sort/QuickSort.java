package com.zy.sort;


import java.util.*;

public class QuickSort {

    public static void main(String[] args){


        //1.数组快排
//        int[] num = {-2,3,1,4,3,6,7,5,8,6};
//        quickSort(num, 0, num.length - 1);
//        for(int n:num){
//            System.out.println(n);
//        }
        //2.链表快排
        Node end = new Node(6, null);
        Node node1 = new Node(8, end);
        Node node2 = new Node(5, node1);
        Node node3 = new Node(7, node2);
        Node node4 = new Node(6, node3);
        Node node5 = new Node(3, node4);
        Node node6 = new Node(4, node5);
        Node head = new Node(8, node6);
        quickSortList(head, null);
        while(head.next != null) {
            System.out.println(head.val);
            head = head.next;
        }


    }

    public static void quickSort(int[] num, int left, int right){
        if( left < right) {
            int mid = findLocation(num, left, right);
            quickSort(num, left, mid);
            quickSort(num, mid + 1, right);
        }
    }

    public static int findLocation(int[] num, int left, int right){
        int l = left;
        int r = right;
        int tmp = num[l];
        while( l < r ){
            while( l<r && num[r]>=tmp ){
                r--;
            }
            if(l < r){
                num[l] = num[r];
                l++;
            }

            while( l<r && num[l]<=tmp ){
                l++;
            }
            if(l<r){
                num[r] = num[l];
                r--;
            }

        }

        num[l] = tmp;
        return l;

    }

    /**
     * 单链表的快速排序
     * @param head
     */
    public static void quickSortList(Node head, Node end) {
        if(head == null || head == end) {
            return ;
        }
        int pivot = head.val;
        Node left = head;
        Node cur = head.next;
        while(cur != end) {
            if(cur.val < pivot) {
                left = left.next;
                swap(left, cur);
            }
            cur = cur.next;
        }
        swap(head, left);
        quickSortList(head, left);
        quickSortList(left.next, end);
        List<Node> list = new ArrayList();
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return 0;
            }
        });
    }

    //节点值交换
    private static void swap(Node left, Node cur) {
        int tmp = left.val;
        left.val = cur.val;
        cur.val = tmp;
    }


}

class Node {
    int val;
    Node next;
    public Node(int val) {
        this.val = val;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
