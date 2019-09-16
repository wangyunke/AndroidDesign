package com.i.designpattern.producer_consumer;

public class Producer implements Runnable {
    private ShopQueue queue;

    public Producer(ShopQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            queue.onProduct();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
