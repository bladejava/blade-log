package com.blade.log;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author biezhi
 * @date 2018/3/29
 */
@Slf4j
public class ThroughputTest {

    private static String        record_100_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";   //100字节
    private static String        record_200_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";   //200字节
    //400字节
    private static String        record_400_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";
    private static AtomicInteger messageCount    = new AtomicInteger(0);
    private static int           count           = 1000000;  //基准数值，以messageCount为准
    private static int           threadNum       = 1;  //1,2,4,8,16,32

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(threadNum);

        long st = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                while (messageCount.get() < count) {
                    log.info(messageCount.get() + ":::::" + record_400_byte);
                    messageCount.incrementAndGet();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        long et = System.currentTimeMillis();

        System.out.println("messageCount=" + messageCount.get() + ", threadNum=" + threadNum + ", costTime=" + (et - st) + "ms, throughput=" + (1 * 1000 * messageCount.get() / (et - st)));
        System.exit(0);
    }

}