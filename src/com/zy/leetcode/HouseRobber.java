package com.zy.leetcode;

/**
 * leetcode https://leetcode.com/submissions/detail/110143856/
 */
public class HouseRobber {

    public static void main(String[] args) {
        int[] arr = {2,1,1,2};
        System.out.println(rob(arr));
    }

    public static int rob(int[] nums) {
        int include = 0;
        int exclude = 0;

        for(int k=0;k<nums.length;k++) {
            int i = include;  //包含前一个的最大值
            int e = exclude;  //不包含前一个的最大值
            include = e + nums[i]; //包含当前的最大值
            exclude = Math.max(i,e); //不包含当前的最大值

        }
        return Math.max(include, exclude);
    }
}
