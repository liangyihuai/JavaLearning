package com.huai.leetcode;

import java.util.HashSet;
import java.util.Set;

public class No202_HappyNumber {
    public static void main(String[] args) {
        System.out.println(new No202_HappyNumber().isHappy(19));
    }

    public boolean isHappy(int n) {
        if(n == 1) return true;

        Set<Integer> set = new HashSet<>();

        int target = n;
        int sum = 0;
        while(!set.contains(target)){
            set.add(target);
            while(target > 9){
                int temp = target % 10;
                sum += temp * temp;
                target /= 10;
            }
            sum += target * target;

            if(sum == 1) return true;

            target = sum;
            sum = 0;
        }
        return false;
    }
}
