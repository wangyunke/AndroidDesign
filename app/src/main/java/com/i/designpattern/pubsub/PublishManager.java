package com.i.designpattern.pubsub;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布订阅模式
 * <p>
 * 和观察者模式区别：多了个发布调度中心PublishManager
 */
public class PublishManager {
    private static List<ISubscriber> list = new ArrayList<>();

    public static void register(ISubscriber subscriber) {
        list.add(subscriber);
    }

    public static void unregister(ISubscriber subscriber) {
        list.remove(subscriber);
    }

    public static void notifyObservers() {
        if (list != null && list.size() > 0) {
            for (ISubscriber subscriber : list) {
                subscriber.update();
            }
        }
    }
}
