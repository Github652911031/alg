package com.zy.string;

/**
 * 最长回文子串问题：给定一个字符串，求它的最长回文子串长度。
 如果一个字符串正着读和反着读是一样的，那它就是回文串。
 e.g. 12321 a aba abba aaaa tattarrattat

 wait for test
 */
public class Manacher {

    /**
     * 最简单粗暴的办法是：找到字符串的所有子串，遍历每一个子串以验证它们是否为回文串。
     * 一个子串由子串的起点和终点确定，因此对于一个长度为n的字符串，共有n^2个子串。
     * 这些子串的平均长度大约是n/2，因此这个解法的时间复杂度是O(n^3)。
     * @param s
     * @return
     */
    public int bruteForce(String s){

        return 1;
    }

    /**
     * Manacher算法首先对字符串做一个预处理，
     * 在所有的空隙位置(包括首尾)插入同样的符号，
     * 要求这个符号是不会在原串中出现的。
     * 这样会使得所有的串都是奇数长度的。
     * aba  ———>  #a#b#a#
     * @param origin
     * @return
     */
    public String process(String origin){
       StringBuilder sb = new StringBuilder();
       sb.append('#');
       for(int i=0;i<origin.length();i++){
           sb.append(origin.charAt(i));
           sb.append('#');
       }
       return sb.toString();
    }

    /**我们把一个回文串中最左或最右位置的字符与其对称轴的距离称为回文半径。
     * Manacher定义了一个回文半径数组RL，用RL[i]表示以第i个字符为对称轴的回文串的回文半径。
     *我们一般对字符串从左往右处理，因此这里定义RL[i]为第i个字符为对称轴的回文串的最右一个字符与字符i的距离。
     * 对于上面插入分隔符之后的两个串，可以得到RL数组：
     * char:    # a # b # a #
        RL :    1 2 1 4 1 2 1
        RL-1:   0 1 0 3 0 1 0
        i :     0 1 2 3 4 5 6
     *通过观察可以发现，RL[i]-1的值，正是在原本那个没有插入过分隔符的串中，以位置i为对称轴的最长回文串的长度。
     * 那么只要我们求出了RL数组，就能得到最长回文子串的长度。
     * 于是问题变成了，怎样高效地求的RL数组。基本思路是利用回文串的对称性，扩展回文串。
     * @see <a href="https://segmentfault.com/a/1190000003914228">https://segmentfault.com/a/1190000003914228</a>
     * @param s
     * @return
     */

    public int manacher(String s){
        s = process(s);
        int[] RL = new int[s.length()]; //RL数组
        int maxRight = 0;      //当前访问到的所有回文子串，所能触及的最右一个字符的位置
        int pos = 0;          //MaxRight对应的回文串的对称轴所在的位置
        int maxLen = 0;
        //从左往右地访问字符串来求RL，假设当前访问到的位置为i，即要求RL[i]
        for(int i=0;i<s.length();i++){
            //i必然是在pos右边的(pos已经访问过)
            //分类讨论
            if(i < maxRight ) { //当i在MaxRight的左边
                int j = 2 * pos - i;  //i关于pos的对称位置j

                RL[i] = Math.min(RL[j], maxRight - i);
            }else{
                RL[i] = 1;
            }
            while (i - RL[i] >= 0 && i + RL[i] < s.length() && s.charAt(i - RL[i]) == s.charAt(i + RL[i])) {
                RL[i]++;
            }
            if(i + RL[i] - 1> maxRight){
                maxRight = i + RL[i] -1;
                pos = i;
            }
            maxLen = Math.max(maxLen, RL[i] - 1);

        }
        return maxLen;

    }

    public static void main(String[] args){
        System.out.println(new Manacher().manacher("ababacc"));
    }
}
