package com.zy.DesignPattern.Singleton;

/**
 * 饿汉，变种，和方式三差不多，都是在类初始化时实例化instance
 */
public class Singleton4 {
    private  static Singleton4 instance ;
    private Singleton4(){}
    static {
        instance = new Singleton4();
    }
    public static Singleton4 getInstance(){
        return instance;
    }

}
