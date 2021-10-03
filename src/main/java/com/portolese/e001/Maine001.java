package com.portolese.e001;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Maine001 {

    public static void main(String[] args){

//        ThreadFactory threadFactory = new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r,"AsyncThreadPool-");
//            }
//        };

        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                        .namingPattern("AsyncThreadPool-%d")
                        .daemon(true)
                        .priority(Thread.NORM_PRIORITY)
                        .build();

//        ExecutorService executorService = Executors.newSingleThreadExecutor(factory);
        ExecutorService executorService = Executors.newFixedThreadPool(10,factory);

        Future<?> aFuture = executorService.submit(new RunnableAction("A", 10000));
        Future<?> bFuture = executorService.submit(new RunnableAction("B", 100000));
        try {
            aFuture.get();
            bFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Runnable> runnables = executorService.shutdownNow();

    }
}
