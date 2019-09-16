package com.i.designpattern.producer_consumer;

public class Test {

	public static void main(String args[]) {
		ShopQueue queue = new ShopQueue();
		Thread customer = new Thread(new Consumer(queue));
		Thread product = new Thread(new Producer(queue));
		
		customer.start();
		product.start();
	}
}
