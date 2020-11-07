package com.zy.binarySearch;

import java.util.Arrays;

/**
 * 二分查找及其变种
 */
public class SearchBasic {
    public static void main(String[] args) {
        int[] arr = {1,1,2,2,33,44,55,66};
        System.out.println(Arrays.binarySearch(arr, 2));
    }

    /**
     * 找到该值在数组中的下表，否则为-1
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] > key) {
                right = mid - 1;
            }else if(array[mid] < key) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找第一个与key相等的元素
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch1(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] >= key) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(left < array.length && array[left] == key) {
            return left;
        }
        return -1;
    }

    /**
     * 查找最后一个与key相等的元素
     */
    public static int binarySearch2(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] <= key) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(right < array.length && array[right] == key) {
            return right;
        }
        return -1;
    }

    /**
     * 查找最后一个小于或者等于key的元素
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch3(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] <= key) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 查找最后一个小于Key的元素
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch4(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] >= key) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }

    /****
     * 查找第一个等于或者大于key的元素
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch5(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] >= key) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    /****
     * 查找第一个大于key的元素
     * @param array
     * @param key
     * @return
     */
    public static int binarySearch6(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(array[mid] > key) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }


}
