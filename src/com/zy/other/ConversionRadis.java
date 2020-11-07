package com.zy.other;

import java.util.Stack;

/**
 * 进制数之间的转换
 */
public class ConversionRadis {
    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String s = new String(array);

    //将十进制转换为N进制
    public static String ten_to_N(int number, int N) {
        StringBuilder sb = new StringBuilder();
        while(number > 0){
            int temp = number % N;
            sb.insert(0,array[temp]);
            number = number / N;
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int ten = 190;
        int radis = 16;
        System.out.println(N_to_Ten("ab", radis));
//        System.out.println(s);
//        System.out.println(s.indexOf('a'));
    }


    //将N进制转换为10进制
    public static int N_to_Ten(String number, int N){
        int base = 1;
        char[] arr = number.toCharArray();
        int result = 0;
        for(int i=arr.length-1;i>=0;i--){
            int tmp = s.indexOf(arr[i]);
            result += tmp * base;
            base = base * N;

        }
        return result;
    }
}
