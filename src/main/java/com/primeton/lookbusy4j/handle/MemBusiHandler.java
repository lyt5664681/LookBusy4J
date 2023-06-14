package com.primeton.lookbusy4j.handle;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Component
public class MemBusiHandler {

    @Value("${busy.mem-polling.enable:false}")
    private boolean enable;

    @Value("${busy.index:100}")
    private int busiIndex;

    @Value("${busy.thread.count:50}")
    private int numThreads;

    public static Map cache = new HashMap(2048);

    @Scheduled(fixedDelayString = "${busy.mem-polling.time:50000}")
    public void load(){
        if(!enable) {
            return;
        }
        ThreadFactory threadFactory = ThreadFactoryBuilder.create()
                .setNamePrefix("MEM-ThreadPool-")
                .build();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads, threadFactory);

        for (int i = 0; i < numThreads; i++) {
            executor.execute(MemBusiHandler::consumeMem);
        }

        executor.shutdown();
    }

    public static void consumeMem() {
        List<byte[]> memoryList = new ArrayList<>();

        try {
            while (true) {
                byte[] data = generateRandomData();
                memoryList.add(data);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("consumeMEM limit out2");
        }
        System.out.println("Memory occupation finished.1");
    }

    private static byte[] generateRandomData() {
        int dataSize = 1024 * 1024 * 100; // 每次生成100MB的数据
        byte[] data = new byte[dataSize];
        new Random().nextBytes(data);
        cache.put(data,data.toString());
        return data;
    }
}
