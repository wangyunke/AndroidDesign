package com.i.designpattern.pubsub;

/**
 * Created by ykw on 2016/6/27.
 * 订阅者
 */
public class Subscriber1 implements ISubscriber {
    private String name;

    public Subscriber1(String name) {
        this.name = name;
        PublishManager.register(this);
    }

    @Override
    public void update() {
        System.out.println(name + ": 自身发生更新");
    }

    @Override
    public void notifyAllChange() {
        System.out.println(name + ": 通知发生变化");
        PublishManager.notifyObservers();
    }

}
