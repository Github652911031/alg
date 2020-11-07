package com.zy.sort;



/**
 * 归并排序
 */
public class MergeSort {
    /**
     * 归并排序main函数（数组形式）
     * @param args
     */
//    public static void main(String[] args) {
//        int[] num = {-2, 3, 1, 4, 3, 6, 7, 5, 8, 6};
//        int[] aux = new int[num.length];
//        mergeSort(num, 0, num.length - 1, aux);
//        for (int n : num) {
//            System.out.println(n);
//        }
//
//    }

    /**
     * 归并排序链表形式
     * @param args
     */
    public static void main(String[] args){
        ListNode one = new ListNode(4);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(5);
        ListNode four = new ListNode(1);
        ListNode five = new ListNode(6);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode result = sortList(one);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    /**
     * 输入为数组
     *
     * @param num
     * @param left
     * @param right
     * @param aux
     */
    public static void mergeSort(int[] num, int left, int right, int[] aux) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(num, left, mid, aux);
            mergeSort(num, mid + 1, right, aux);
            merge(num, left, mid, right, aux);
        }

    }

    private static void merge(int[] num, int left, int mid, int right, int[] aux) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (num[i] < num[j]) {
                aux[k++] = num[i++];
            } else {
                aux[k++] = num[j++];
            }
        }
        while (j <= right) {
            aux[k++] = num[j++];
        }
        while (i <= mid) {
            aux[k++] = num[i++];
        }
        for (i = left; i <= right; i++) {
            num[i] = aux[i - left];
        }
    }


    /****************************链表 归并*******************************************************/
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //Sort a linked list in O(n log n) time using constant space complexity.
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;
        left = sortList(left);
        right = sortList(right);
        return mergeList(left,right);

    }

    public static ListNode findMiddle(ListNode node){
        ListNode slow = node;
        ListNode fast = node;
        while(fast.next != null && fast.next.next != null ){
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }

    private static ListNode mergeList(ListNode list1, ListNode list2) {
        ListNode dumplist = new ListNode(0);
        ListNode head = dumplist;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                dumplist.next = list1;
                list1 = list1.next;

            }else{
                dumplist.next = list2;
                list2 = list2.next;
            }
            dumplist = dumplist.next;
        }
        if(list1 == null){
            dumplist.next = list2;

        }
        if(list2 == null){
            dumplist.next = list1;
        }
        return head.next;
    }

}

