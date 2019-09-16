package com.i.designpattern.producer_consumer;

public class Consumer implements Runnable {

	private ShopQueue queue;

	public Consumer(ShopQueue queue) {
		this.queue = queue;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			queue.onCousume();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
