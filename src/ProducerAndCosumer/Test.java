package ProducerAndCosumer;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args){
        // 仓库对象
        Storage storage = new Storage1();

        // 生产者对象
        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);
        Producer p5 = new Producer(storage);
        Producer p6 = new Producer(storage);
        Producer p7 = new Producer(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
//        c1.start();
//        c2.start();
//        c3.start();
//        p1.start();
//        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();
//        p6.start();
//        p7.start();

        //采用线程池进行管理
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(c1);
        exec.execute(c2);
        exec.execute(c3);
        exec.execute(p1);
        exec.execute(p2);
        exec.execute(p3);
        exec.execute(p4);
        exec.execute(p5);
        exec.execute(p6);
        exec.execute(p7);

        try {
            p1.join();
            c1.join();
            c2.join();
            c3.join();
            p2.join();
            p3.join();
            p4.join();
            p5.join();
            p6.join();
            p7.join();
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(storage.getMAX_SIZE());
    }

}
