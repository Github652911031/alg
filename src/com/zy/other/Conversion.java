package com.zy.other;

/**
 * 给出一个序列：A,B,C...Z,AA,AB,AC,..AZ,BA,....AAA....
 * 输入一个数字，返回对应的字符串
 * 如：输入27,返回AA
 * 注意和进制转换的区别在于：进制转换A不可能出现在最高位
 */

public class Conversion {

    public static void main(String[] args){
        String s = conversionIntToStr(1352);
        System.out.println(s);
        int a = conversionStrToInt(s);
        System.out.println(a);

    }

    /**
     * 实现数字到字符串的转换
     * @param num
     * @return
     */
    public static String conversionIntToStr(int num){
        char[] word = {'A','B','C','D','E','F','G',
                        'H','I','J','K','L','M','N',
                        'O','P','Q','R','S','T',
                        'U','V','W','X','Y','Z'};

        StringBuilder sb = new StringBuilder();
        while( num > 0 ){
            int a = num%26;
            if( a == 0 ){
                sb.insert(0,word[25]);
                num = num - 1;
            }else{
                sb.insert(0, word[a-1]);
            }
            num = num / 26;

        }
        return sb.toString();
    }

    /**
     * 实现字符串到数字的转换
     * @param s
     * @return
     */
    public static int conversionStrToInt(String s){
        int len = s.length();
        int result = 0;
        int j = 0;
        int m = 1;  //位置权重
        for(int i=len-1;i>=0;i--,j++){
            int temp = s.charAt(i) - 'A' + 1;
            if(j>=1){
                m = m * 26;
            }
            result = result + temp * m;
        }
        return result;
    }


}
