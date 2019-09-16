package com.i.designpattern;

import com.i.designpattern.producer_consumer.Consumer;
import com.i.designpattern.producer_consumer.Producer;
import com.i.designpattern.producer_consumer.ShopQueue;

import org.junit.Test;


/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class ProducerConsumerTest {

    @Test
    public void testShopQueue() {
        ShopQueue queue = new ShopQueue();
        Thread customer = new Thread(new Consumer(queue));
        Thread product = new Thread(new Producer(queue));

        customer.start();
        product.start();
    }
}
