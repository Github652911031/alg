package com.zy.leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * 刪除排序链表中的重复值
 * e.g. Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 */
public class RemoveDuplicatesFromSortList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode fakehead = new ListNode(-1);
        fakehead.next = head;
        ListNode pre = fakehead;
        ListNode cur = head;
        boolean isDelete = false;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
                isDelete = true;
            } else if (isDelete) {
                cur = cur.next;
                pre.next = cur;
                isDelete = false;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return fakehead.next;
    }

}
