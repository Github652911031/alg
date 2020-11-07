package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] numbers = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = threeSum(numbers);
    }

    public static List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> result = new ArrayList();
        if(numbers == null || numbers.length == 0) {
            return result;
        }
        Arrays.sort(numbers);
        for(int i=0;i<numbers.length - 2;) {
            int first = i + 1;
            int last = numbers.length - 1;
            while(first < last) {
                if(numbers[i] * numbers[last] > 0) break;

                int sum = numbers[i] + numbers[first] + numbers[last];
                if(sum == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(numbers[i]);
                    list.add(numbers[first]);
                    list.add(numbers[last]);
                    result.add(list);
                }
                if (sum >= 0) {
                    while(first < last) {
                        if(numbers[last] == numbers[--last]) {
                            continue;
                        }
                    }
                }else if (sum < 0) {
                    while(first < last) {
                        if(numbers[first] == numbers[++first]) {
                            continue;
                        }
                    }
                }
            }
            while(i < numbers.length - 2 && numbers[i] == numbers[++i]){}

        }
        return result;
    }
}
