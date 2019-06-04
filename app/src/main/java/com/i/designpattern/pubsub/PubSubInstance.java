package com.i.designpattern.pubsub;

/**
 *
 */
public class PubSubInstance {

    public static void use() {
        Subscriber1 subscriber1 = new Subscriber1("Subscriber1");
        Subscriber2 subscriber2 = new Subscriber2("Subscriber2");

//        subscriber1.notifyAllChange();
        subscriber2.notifyAllChange();
    }

}
