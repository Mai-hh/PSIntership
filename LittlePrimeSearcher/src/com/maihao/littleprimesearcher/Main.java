package com.maihao.littleprimesearcher;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        long startTime = System.nanoTime();
        int n = solution.searchPrimes(1000000);
        // todo：多线程解决
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println(n + " 耗时：" + time);
    }

}

// todo 面向对象特征
class Solution {

    public int searchPrimes(int n) {

        // 标记数组
        boolean[] isPrime = new boolean[n];

        // 技术器
        int sum = 0;
        Arrays.fill(isPrime, true);

        // 最小的素数是2， 进行一波简单的筛
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                sum++;
            }
        }

        return sum;
    }
}
