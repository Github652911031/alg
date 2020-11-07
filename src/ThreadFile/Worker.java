package ThreadFile;

import java.io.PrintWriter;

public class Worker implements Runnable {

    //静态变量，全局共享，记录打印次数
        private static int count = 0;

    //所有的ThreadGroup类对象共享一个锁，用于count变量的同步，任何一个线程需要修改count变量，必须取得该锁
        private static Object lock=new Object();

        /*
        ----------以上是静态变量区-------------------------------------------------------------------------------
         */
        private int[] record;
        private PrintWriter printWriter;
        private int type=0;  //0:打印偶数, 1:打印奇数
        private int OddStart = 0; //奇数起始位置
        private int EvenStart = 0; //偶数起始位置

        public Worker(int[] record, PrintWriter pw){
            this.record = record;
            this.printWriter = pw;

        }
        @Override
        public void run() {
            while(print());
            this.printWriter.flush();
            this.printWriter.close();
        }

        public synchronized boolean print(){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName());
            for(int i=0;i<10;){
                if(EvenStart >= record.length && OddStart >= record.length){
                    notifyAll();
                    return false;
                }
                if( type == 0 ){
                    if(EvenStart >= record.length){
                        break;
                    }
                    if((record[EvenStart] & 1) == 0 ){
                        printWriter.println(record[EvenStart]+",");
//                        printWriter.flush();
                        synchronized(lock){
                            count++;
//                            System.out.println(count);
                            if(count%1000 == 0){
                                System.out.println("当前完成数量："+count);
                            }
                        }
                        i++;
                    }

                    EvenStart++;

                }else{
                    if(OddStart >= record.length){
                        break;
                    }
                    if((record[OddStart] & 1) == 1 ){
                        printWriter.println(record[OddStart]+",");
//                        printWriter.flush();
                        synchronized (lock){
                            count++;
//                            System.out.println(count);
                            if(count%1000 == 0){
                                System.out.println("当前完成数量："+count);
                            }
                        }

                        i++;
                    }

                    OddStart++;

                }
            }
            type ^= 1 ;
            notifyAll();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }



}
