package run;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class AsyncAssertions {
    public static boolean isTrueWithin(Supplier<Boolean> predicate, int timeout, TimeUnit timeUnit) {
        boolean validated;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        try {
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                if (predicate.get()) {
                    countDownLatch.countDown();
                }
            }, 0, 10, TimeUnit.MILLISECONDS);
            try {
                validated = countDownLatch.await(timeout, timeUnit);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            scheduledExecutorService.shutdown();
        }
        return validated;
    }
}