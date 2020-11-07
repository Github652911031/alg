package com.zy.sort;

public class HeapSort {
    /**
     * 堆排序可以参考jdk中的优先级队列实现，但要注意：
     * 1.在JDK中,优先级队列的add 和 poll方法分别调用了siftUp和siftDown(具体来说是iftUpComparable和
     * siftDownComparable),两种方法实现方式不一样.从函数名称可以看出,siftUpComparable是上浮操作，用于添加到
     * 数组末尾.siftDownComparable是元素下降操作,用于元素出队.
     * 2.堆排序即一个优先级队列的元素出队过程
     * @param args
     */

    //堆排序,以小根堆为例,即根节点数最小
    public static void main(String[] args){
        int[] num = {-2,3,1,4,3,6,7,5,8,2};
        heapSort(num);
//        for(int n:num){
//            System.out.println(n);
//        }

    }

    public static void buildheap(int num[]){
        int heapsize = num.length;
        for( int i = heapsize / 2 - 1; i >= 0; i-- ){
            heapify(num, i, heapsize);
        }
    }


    public static void  heapify(int num[], int i, int n){
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int smalllest = i;
        int len = n;
        while( leftChild < n){
            if(num[leftChild] < num[smalllest]){
                smalllest = leftChild;
            }
            if(rightChild < n && num[rightChild] < num[smalllest]){
                smalllest = rightChild;
            }
            if(smalllest != i){
                int tmp = num[i];
                num[i] = num[smalllest];
                num[smalllest] = tmp;
            }else{
                break;
            }
            i = smalllest;
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;

        }
    }

    public static void heapSort(int[] num){
        buildheap(num);
        for(int i = num.length - 1;i>=0;i--){
            System.out.println(num[0]);
            num[0] = num[i];
            heapify(num,0, i);
        }
    }
}
