package com.maihao.littleprimesearcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数n,计算区间 0~n 质数的个数: ");
        int number = scanner.nextInt();

        SingleThreadSolution singleThreadSolution = new SingleThreadSolution();
        long startTime = System.nanoTime();
        int n = singleThreadSolution.searchPrimes(number);
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("质数的个数：" + n + " 单线程埃氏筛法耗时：" + time);

        ThreadsSolution threadsSolution = new ThreadsSolution();
        long startTime1 = System.nanoTime();
        int n1 = threadsSolution.searchPrimes(number);
        long endTime1 = System.nanoTime();
        long time1 = endTime1 - startTime1;
        System.out.println("质数的个数：" + n1 + " 多线程普通筛法耗时：" + time1);

        long startTime2 = System.nanoTime();
        int n2 = threadsSolution.countPrimes(2,number);
        long endTime2 = System.nanoTime();
        long time2 = endTime2 - startTime2;
        System.out.println("质数的个数：" + n2 + " 单线程普通筛法耗时：" + time2);
    }

}

class SingleThreadSolution {

    public int searchPrimes(int n) {

        // 标记数组
        boolean[] isPrime = new boolean[n];

        // 计数器
        int sum = 0;
        Arrays.fill(isPrime, true);

        for (int i = 2; i < Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i*i; j < n; j+=i) {
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

class ThreadsSolution {

    // 普通的质数判断
    public boolean isPrime(int number) {
        if(number <= 1) return false;
        for(int i = 2; i <= Math.sqrt(number); i++)
            if(number % i == 0) return false;
        return true;
    }

    public int countPrimes(int start, int end) {
        int total=0;
        for (int i = start; i <= end; i++)
            if(isPrime(i)) total++;
        return total;
    }

    public int searchPrimes(int number) {
        int sum = 0; // 计数器
        try {

            // 存放Callable接口
            List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();

            // 分成10份任务
            int chunk = number / 5;
            for(int i = 0; i < 5; i++){
                // 1-10000, 10001-20000.... 90001-100000
                int start = (i * chunk) + 1;
                int end = start + chunk - 1;
                // 每个Callable接口返回的是区间内素数个数
                partitions.add(new Callable<Integer>() {
                    @Override
                    public Integer call() {
                        return countPrimes(start, end);
                    }
                });
            }

            // 在线程池中创建10个线程,进行一个懒的偷
            ExecutorService Pool = Executors.newFixedThreadPool(5);

            // 等待返回所有Future结果
            List<Future<Integer>> results = Pool.invokeAll(partitions,777, TimeUnit.SECONDS);

            // 执行完成之后关闭线程池
            Pool.shutdown();

            // 取出结果
            for (Future<Integer> result : results) {
                sum += result.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sum;
    }
}
