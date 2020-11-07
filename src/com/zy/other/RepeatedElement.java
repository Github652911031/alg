package com.zy.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小易有一个长度为n序列，小易想移除掉里面的重复元素，
 * 但是小易想是对于每种元素保留最后出现的那个。小易遇到了困难,希望你来帮助他。
 */

/**
 * 此题巧妙地用到了List去重的方法。
 * 比如说有一组数，为1 5 5 1 6 1
 * 从[0,n-1]使用List去重之后，保存地其实就是每种元素最开始出现的那个元素。
 * 但是我们换个角度去想，如果我们从后向前遍历，保存地就是每种元素最后出现的那个元素，只不过相对从前到后来说，数倒过来了。
 * 我们最终将数反转过来即可。
 * 时间复杂度为O(n)
 * @author 何嘉龙
 *
 */

public class RepeatedElement {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            List<Integer> lists = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            for(int i = n - 1; i >= 0; --i) {
                if(!lists.contains(arr[i])) {
                    lists.add(arr[i]);
                }
            }
            for(int i = lists.size() - 1; i >= 0; --i) {
                System.out.print(lists.get(i));
                if(i != 0) {
                    System.out.print(" ");
                }
            }
        }
        in.close();
    }
}
