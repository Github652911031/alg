package com.zy.other;

import java.util.List;

/**
 * 有一个无序整型数组，如何求出这个数组排序后的任意两个相邻元素的最大差值？要求时间和空间复杂度尽可能低。
 * 例如：无序数组 2、3、1、4、6，排序后是1、2、3、4、6，最大差值是 6-4=2
 * 最直接方法:用一种较快的稳定排序算法（比如归并算法，时间复杂度N*logN）给原数组排序，
 * 然后遍历排好序的数组，每两个相邻元素求差，最终得到最大差值。
 * 该解法的时间复杂度是O（N*logN），在不改变原数组的情况下，空间复杂度是O（N）
 * 省略不写
 */
public class MaxDiffAdjacentEle {

    /**
     * 1.利用计数排序的思想，先求出原数组的最大值Max与最小值Min的区间长度k（k=Max-Min+1）。
     * 2.创建一个长度为k的新数组Array。
     * 3.遍历原数组，把原数组每一个元素插入到新数组Array对应的位置，比如元素的值为n，则插入到Array[n-min]当中。此时Array的部分位置为空，部分位置填充了数值。
     * 4.遍历新数组Array，统计出Array中最大连续出现空值的次数+1，即为相邻元素最大差值。
     * 该解法的时间复杂度为O（n+k），空间复杂度同样是O（n+k）。
     * @param arr
     * @return
     */
    public static int countSort(int[] arr){
        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i] > maxV){
                maxV = arr[i];
            }
            if(arr[i] < minV){
                minV = arr[i];
            }
        }
        int k = maxV - minV + 1;
        int[] arrNew = new int[k];
        for(int i=0;i<arr.length;i++){
            arrNew[arr[i]-minV] = arr[i];
        }
        int result = 1;
        int tmp = 1;
        for(int i=0;i<arrNew.length;i++){
            if(arrNew[i] == 0){
                tmp = tmp + 1;
            }else{
                result = Math.max(result, tmp);
                tmp = 1;
            }
        }
        return result;
    }

    /**上面解法在数组元素范围不是很悬殊的时候还可以
     * 可是设想一下，如果数组里只有3个数：1,2,100000,就要创建长度100000的数组！
     * 因此，
     * 1.利用桶排序的思想，先求出原数组从最小值Min到最大值Max的单位区间长度d，d=(Max-Min)/n.算出
     * d的作用是为了后续确定各个桶的区间范围划分。
     * 2.创建一个长度是N+1的数组Array,数组的每一个元素都是一个List,代表一个桶
     * 3.遍历原数组,把数组每一个元素插入到新数组Array对应的桶当中，进入各个桶的条件是根据不同的数值区分。由于桶的总数量是
     * n+1,因此至少有一个桶是空的
     * 4.遍历新数组Array，计算每一个空桶右端非空桶中的最小值，与空桶左端非空桶的最大值的差，数值最大的差即为原数组排序后的相邻最大差值。
     * 该解法的时间复杂度为O（n），空间复杂度同样是O（n）。
     *
     * 为什么要把数组元素插入桶当中呢？我只需要记录下每个桶的最大最小值不就好了嘛？。。
     * @see <a href="http://blog.jobbole.com/108594/">http://blog.jobbole.com/108594/</a>
     * @param arr
     * @return
     */
    public static int bucketSort(int[] arr, int n){
        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i] > maxV){
                maxV = arr[i];
            }
            if(arr[i] < minV){
                minV = arr[i];
            }
        }
        int d = ((maxV - minV)+n-1)/n ;
        Node[] arrNew = new Node[n+1];
        for(int i=0;i<arr.length;i++){
            putValintoBucket(arrNew, (arr[i]-minV)/d,arr[i]);
        }
        int result = arrNew[0].max - arrNew[0].min;
        int lastmax = arrNew[0].max;
        for(int i=1;i<arrNew.length;i++){
            if(arrNew[i] == null){
                continue;
            }
            result = Math.max(result,arrNew[i].min - lastmax);
            lastmax = arrNew[i].max;
        }
        return result;
    }

    public static void putValintoBucket(Node[] arrNew, int index, int val){
        if(arrNew[index] == null){
            arrNew[index] = new Node(val);
            arrNew[index].max = val;
            arrNew[index].min = val;
        }else{
            if(val < arrNew[index].min){
                arrNew[index].min = val;
            }
            if(val > arrNew[index].max){
                arrNew[index].max = val;
            }
        }
    }

    //创建内部类
    static class Node{
        int val;
        int min; //记录这条链上的最小值
        int max; //记录这条链上的最大值

        public Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        int[] arr = {2,3,1,4,6};
        System.out.println(countSort(arr));
        System.out.println(bucketSort(arr,4));
    }






}
