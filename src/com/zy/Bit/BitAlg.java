package com.zy.Bit;

import com.zy.DesignPattern.Singleton.Singleton6;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashSet;

public class BitAlg {

    //运用位运算实现不用额外的变量实现两数交换
    public static void swap(int a, int b){
        System.out.println("原来两数：" + a + "," + b);
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("交换后两数：" + a + "," + b);

    }

    //生成一个集合的所有子集合
    //比如说我们有一个集合{2,3,4},那么它的子集合即为{},{2},{3},{4},{2,3},{2,4},{3,4},{2,3,4}
    //一共有8个
    //运用位操作来做的话，将0到7的每个数字转成二进制，如果第i位为0,则表示子集中不包括原集中的第i位
    //000 空集
    //001 {4}
    //010 {3}
    //011 {3,4}...
    public static void generateSubSet(HashSet<Integer> set){
        //todo
    }




    public static void main(String[] args){
        swap(3,4);  //两数交换


    }
}
