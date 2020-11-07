package com.zy.DesignPattern.Singleton;

/**
 * 饿汉
 * 这种方式基于classloader机制，非Lazy loading，线程安全
 */
public class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    private Singleton3(){}

    public static Singleton3 getInstance(){
        return instance;
    }

}
