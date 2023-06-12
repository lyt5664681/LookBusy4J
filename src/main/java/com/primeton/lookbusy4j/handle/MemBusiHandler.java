package com.primeton.lookbusy4j.handle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MemBusiHandler {

    @Value("${busy.mem-polling.enable:false}")
    private boolean enable;

    @Scheduled(fixedDelayString = "${busy.mem-polling.time:50000}")
    public void load(){
        if(!enable) {
            return;
        }
        System.out.println("mem begin load");
        consumeMem();
    }

    public void consumeMem() {
        List<byte[]> memoryList = new ArrayList<>();

        try {
            while (true) {
                byte[] data = generateRandomData();
                memoryList.add(data);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("consumeMEM limit out");
        }
        System.out.println("Memory occupation finished.");
    }

    private static byte[] generateRandomData() {
        int dataSize = 1024 * 1024 * 10; // 每次生成1MB的数据
        byte[] data = new byte[dataSize];
        new Random().nextBytes(data);
        return data;
    }
}
