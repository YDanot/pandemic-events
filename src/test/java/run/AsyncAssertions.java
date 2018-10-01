package run;

import org.assertj.core.api.Assert;

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

    public static Assert within(Supplier<Assert> a, int timeout, TimeUnit timeUnit) {
        final Wrapper<Assert> wrapper = new Wrapper<>();
        final Wrapper<AssertionError> errorWrapper = new Wrapper<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        try {
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                try {

                    Assert anAssert = a.get();
                    wrapper.set(anAssert);
                    errorWrapper.set(null);
                    countDownLatch.countDown();
                } catch (AssertionError e) {
                    errorWrapper.set(e);
                }
            }, 0, 10, TimeUnit.MILLISECONDS);
            try {
                countDownLatch.await(timeout, timeUnit);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            scheduledExecutorService.shutdown();
        }
        if (errorWrapper.get() != null)
            throw errorWrapper.get();

        return wrapper.get();
    }

    private static class Wrapper<T> {
        private T object;

        T get() {
            return object;
        }

        void set(T object) {
            this.object = object;
        }
    }
}