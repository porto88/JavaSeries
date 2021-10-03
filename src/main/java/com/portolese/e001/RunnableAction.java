package com.portolese.e001;

public class RunnableAction implements Runnable {

    private String uniqueString = "";
    private int sleepTime = 0;

    public RunnableAction(String uniqueString, final int sleepTime) {
        this.uniqueString = uniqueString;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Starting: " + uniqueString);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Exception: " + uniqueString + " " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Finished: " + uniqueString);
    }
}
