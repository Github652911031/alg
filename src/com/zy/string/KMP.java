package com.zy.string;

/**
 * 字符串KMP匹配算法:有一个文本串S，和一个模式串P，现在要查找P在S中的位置
 */
public class KMP {

    /**
     * 如果用暴力匹配的思路，并假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置，则有：
     如果当前字符匹配成功（即S[i] == P[j]），则i++，j++，继续匹配下一个字符；
     如果失配（即S[i]! = P[j]），令i = i - (j - 1), j = 0。相当于每次匹配失败时,i 回溯,j 被置为0
     * @param s
     * @param p
     * @return
     */
    public int violentMatch(String s, String p){
        int slen = s.length();
        int plen = p.length();

        int i = 0;
        int j = 0;
        while( i < s.length() && j < p.length()){
            if(s.charAt(i) == p.charAt(j)){
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }
        if(j == p.length()){
            return i - j;
        }
        return -1;
    }

    /**
     * 假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置
     如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
     如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
     换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值，即移动的实际位数为：j - next[j]，且此值大于等于1。
     next 数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀。next[0]=-1,next数组下标表示p的第几位
     next[j]表示p中第j位的最长相同前后缀长度
     以"ABCDABD"为例,
     -- "A"的前缀和后缀都为空集，共有元素的长度为0；
     -- "AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
     -- "ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     -- "ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     -- "ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
     -- "ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；
     -- "ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     @see <a href="https://www.zhihu.com/question/21923021">https://www.zhihu.com/question/21923021</a>
     @see <a href="http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html">http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html</a>
     * @param s
     * @param p
     * @return
     */
    public int kmpMatch(String s,String p){
        int slen = s.length();
        int plen = p.length();
        int[] next = createNextArray(p);
        int i = 0;
        int j = 0;
        while( i < s.length() && j < p.length()){
            if(j == -1 || s.charAt(i) == p.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];   //和暴力算法不同之处，注意先前条件也略有不同
            }
        }
        if(j == p.length()){
            return i - j;
        }
        return -1;
    }


    /**
     * 求next数组的过程完全可以看成字符串匹配的过程，即以模式字符串为主字符串，
     * 以模式字符串的前缀为目标字符串，一旦字符串匹配成功，那么当前的next值就是匹配成功的字符串的长度。
     * 就是从模式字符串的第一位(注意，不包括第0位)开始对自身进行匹配运算。
     * 在任一位置，能匹配的最长长度就是当前位置的next值。
     * @param p
     * @return
     */
    private int[] createNextArray(String p) {
        int len = p.length();
        int[] arr = new int[len+1];
        arr[0] = -1;
        int i = 0;
        int j = -1;
        while(i < len){
            if(j == -1 || p.charAt(i) == p.charAt(j)){
                i++;
                j++;
                arr[i] = j;
            }else{
                j = arr[j];
            }
        }
        return arr;

    }

    public static void main(String[] args){
        String s = "helloworld";
        String p = "llow";
        System.out.println(new KMP().violentMatch(s,p));
        System.out.println(new KMP().kmpMatch(s,p));

    }

}
