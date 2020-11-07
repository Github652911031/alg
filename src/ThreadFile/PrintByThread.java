package ThreadFile;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个文件中有10000个数，用Java实现一个多线程程序将这个10000个数输出到5个不用文件中
 * （不要求输出到每个文件中的数量相同）。要求启动10个线程，两两一组，分为5组。
 * 每组两个线程分别将文件中的奇数和偶数输出到该组对应的一个文件中，需要偶数线程每打印10个偶数以后，
 * 就将奇数线程打印10个奇数，如此交替进行。同时需要记录输出进度，每完成1000个数就在控制台中打印当前完成数量，
 * 并在所有线程结束后，在控制台打印”Done”.
 */
public class PrintByThread {

    public static void prepareNumber() throws IOException {
        File file = new File("number.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int a = Math.abs(random.nextInt())%100;
//            System.out.println(a);
            printWriter.print(a+",");
        }
        printWriter.flush();
        printWriter.close();


    }



    public static void main(String[] args){
//        try {
//            prepareNumber();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader("number.txt"));
            String str = br.readLine();
            br.close();
            String[] strs = str.split(",");//将一行字符串全部解析为10000个数字
            System.out.println("strs length:"+ strs.length);
//            System.out.println(strs[9999]);

            for(int i=0;i<5;i++){
                int[] record = new int[2000];
                for(int j=0;j<2000;j++){
//                    System.out.println(strs[i*2000+j]);
                    record[j] = Integer.parseInt(strs[i*2000+j]);
//                    System.out.println(record[j]);
                }
                File file = new File("number_"+i+".txt");
                if(file == null){
                    file.createNewFile();
                }
                PrintWriter pw = new PrintWriter(new FileWriter(file));
                Worker worker = new Worker(record,pw);
                executorService.submit(worker);
                executorService.submit(worker);
//                new Thread(worker,i+"-1").start();
//                new Thread(worker,i+"-2").start();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}


