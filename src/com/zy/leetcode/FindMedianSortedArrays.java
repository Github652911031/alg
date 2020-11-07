package com.zy.leetcode;

import java.util.HashMap;

/**
 * leetcode
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
 * 在两个排序数组中找中位数
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int leftK = (n + m + 1) / 2;
        int rightK = (n + m + 2) / 2;

        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, leftK) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, rightK)) * 0.5;

    }

    /**
     *
     * @param nums1 第一个数组
     * @param start1 第一个数组开始
     * @param end1 第一个数组结束
     * @param nums2 第二个数组
     * @param start2 第二个数组开始
     * @param end2 第二个数组结束
     * @param k 求第K个元素
     * @return
     */
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
         int len1 = end1 - start1 + 1;
         int len2 = end2 - start2 + 1;

        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);

        if(len1 == 0) return nums2[start2 + k - 1];

        if(k == 1) return nums1[start1] < nums2[start2] ? nums1[start1] : nums2[start2];

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k - k / 2) - 1;

        if(nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - ( j - start2 + 1) );
        }else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }

    }

}
