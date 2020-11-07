package com.zy.DesignPattern.Singleton;

/**
 * 静态内部类
 * 同样利用了classloader机制来保证初始化instance只有一个线程，它跟第三种和第四种方式不同的是：
 * 第三种和第四种方式只要Singleton类被装载了，那么instance就会被实例化，而这种方式是singleton类被
 * 装载了，instance不一定被初始化。因为SingletonHolder类没有主动使用，只有显示通过调用getInstance方法
 * 时，才会被显示装载SingletonHolder类，从而实例化instance.
 */
public class Singleton5 {
    private static class SingletonHolder{
        private static final Singleton5 instance = new Singleton5();
    }
    private Singleton5(){
        System.out.println("....");
    }

    private static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args){
        System.out.println("init...");

    }
}
