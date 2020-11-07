package com.zy.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zy.sort.QuickSort.findLocation;

/**
 * 外部排序实现
 * 将大文件分割成小文件，每次从源文件中读取特定行数，利用内部排序（例如快速排序）
 * 将排序后数字放入各个小文件中
 * 然后利用归并排序从每个小文件中合并,由于每个文件都是有序的，可以分行读取
 */
public class ExternalSort {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\java\\alg\\src\\com\\zy\\sort\\source.txt");
        createNewFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //这里是定义我们将源文件中以10000条记录作为单位进行分割。
        final int SIZE=10000;
        //临时存放分割时的记录
        int[] nums=new int[SIZE];
        //保存所有分割文件的名称
        List<String> fileNames=new ArrayList<String>();
        int index=0;
        while (true) {
            String num = bufferedReader.readLine();
            if(num == null) {
                fileNames.add(sortAndSave(nums, index));
                break;
            }
            nums[index] = Integer.valueOf(num);
            index++;
            if(index == SIZE) {
                fileNames.add(sortAndSave(nums, index));
                index = 0;
            }
        }

        bufferedReader.close();
        mergeSort(fileNames);
    }

    //将多个已经排好序的文件进行合并
    private static void mergeSort(List<String> fileNames) throws IOException {
        //记录一次mergeSort后的临时文件
        List<String> tempFileNames = new ArrayList<>();
        for(int i=0;i<fileNames.size();i++) {
            String resultFileName = "D:\\java\\alg\\src\\com\\zy\\sort\\"+System.nanoTime()+".txt";
            File resultFile = new File(resultFileName);
            tempFileNames.add(resultFileName);
            BufferedWriter bw=new BufferedWriter(new FileWriter(resultFile));

            File file1=new File(fileNames.get(i++));
            BufferedReader br1=new BufferedReader(new FileReader(file1));
            if(i<fileNames.size()) {
                File file2 = new File(fileNames.get(i));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                int num1 = 0;
                int num2 = 0;
                boolean isFirst = true;
                boolean firstNext = true;
                String numVal1 = null, numVal2 = null;
                for(;;) {
                    if (isFirst) {
                        numVal1 = br1.readLine();
                        numVal2 = br2.readLine();
                        num1 = Integer.valueOf(numVal1);
                        num2 = Integer.valueOf(numVal2);
                        isFirst = false;
                    }else if(firstNext){
                        numVal1=br1.readLine();
                    }else {
                        numVal2=br2.readLine();
                    }
                    if(numVal1!=null && numVal2!=null) {
                        if (firstNext) {
                            num1 = Integer.valueOf(numVal1);
                        } else {
                            num2 = Integer.valueOf(numVal2);
                        }
                        if(num1<num2){
                            bw.write(num1+"\n");
                            firstNext=true;
                        }else{
                            bw.write(num2+"\n");
                            firstNext=false;
                        }
                    }else{
                        if(numVal1!=null) bw.write(numVal1+"\n");
                        if(numVal2!=null) bw.write(numVal2+"\n");
                        break;
                    }
                }
                while(true){
                    numVal2=br2.readLine();
                    if(numVal2!=null) bw.write(numVal2+"\n");
                    else break;
                }
                br2.close();
                file2.delete();
            }
            while(true){
                String numVal1=br1.readLine();
                if(numVal1!=null){
                    bw.write(numVal1+"\n");
                }
                else break;
            }
            br1.close();
            file1.delete();
            bw.close();
        }

        int size=tempFileNames.size();

        if(size>1){
            mergeSort(tempFileNames);
        }else if(size==1){
            File file=new File(tempFileNames.get(0));
            file.renameTo(new File("D:\\java\\alg\\src\\com\\zy\\sort\\result.txt"));
        }
    }

    //将nums中前index条记录快速排序，然后存入文件，将文件名返回
    private static String sortAndSave(int[] nums, int size) throws IOException {
        Quicksort.sort(nums, 0, size - 1);
        String fileName="D:\\java\\alg\\src\\com\\zy\\sort\\"+System.nanoTime()+".txt";
        File rf = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(rf));
        for(int i=0;i<nums.length;i++) bw.write(nums[i]+ "\n");
        bw.close();
        return fileName;

    }

    public static void createNewFile() throws IOException {
        File file = new File("D:\\java\\alg\\src\\com\\zy\\sort\\source.txt");
        int numCount=10000000;
        Random r=new Random();
        if(file.exists())file.delete();
        FileWriter fw=new FileWriter(file);
        for(int i=0;i<numCount;i++){
            fw.write(r.nextInt()+"\n");
        }
        fw.close();
    }


}

class Quicksort {
    public static void sort(int[] data, int left, int right) {
        if( left < right) {
            int mid = findLocation(data , left, right);
            sort(data, left, mid);
            sort(data, mid + 1, right);
        }
    }

    public static int findLocation(int[] num, int left, int right){
        int l = left;
        int r = right;
        int tmp = num[l];
        while( l < r ){
            while( l<r && num[r]>=tmp ){
                r--;
            }
            if(l < r){
                num[l] = num[r];
                l++;
            }

            while( l<r && num[l]<=tmp ){
                l++;
            }
            if(l<r){
                num[r] = num[l];
                r--;
            }

        }

        num[l] = tmp;
        return l;

    }
}


