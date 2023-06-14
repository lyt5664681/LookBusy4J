package com.primeton.lookbusy4j.handle;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Component
public class CPUBusiHandler {

    @Value("${busy.thread.count:50}")
    private int numThreads;

    @Value("${busy.cpu-polling.enable:false}")
    private boolean enable;

    @Value("${busy.index:100}")
    private int busiIndex;

    @Scheduled(fixedDelayString = "${busy.cpu-polling.time:50000}")
    public void load(){
        if(!enable) {
            return;
        }
        System.out.println("cpu begin load2");
        ThreadFactory threadFactory = ThreadFactoryBuilder.create()
                .setNamePrefix("CPU-ThreadPool-")
                .build();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads, threadFactory);

        for (int i = 0; i < numThreads; i++) {
            executor.execute(CPUBusiHandler::consumeCPU);
        }

        executor.shutdown();
    }

    public static void consumeCPU() {
        int numIterations = 10*100; // 迭代次数

        for (int i = 0; i < numIterations; i++) {
            BigInteger[] numbers = new BigInteger[1000];

            // 生成随机大数
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = generateRandomBigInteger();
            }

            // 排序大数数组
            Arrays.sort(numbers);
        }
    }

    public static BigInteger generateRandomBigInteger() {
        Random random = new Random();

        // 随机生成一个大数，限定位数范围
        int numDigits = 80; // 大数位数
        StringBuilder sb = new StringBuilder(numDigits);
        sb.append(random.nextInt(9) + 1); // 第一位不能为0
        for (int i = 1; i < numDigits; i++) {
            sb.append(random.nextInt(10));
        }

        return new BigInteger(sb.toString());
    }
}
