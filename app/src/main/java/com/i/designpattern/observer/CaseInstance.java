package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public class CaseInstance {

    public static void use() {
        Observer observer1 = new Observer("activity1");
        Observer observer2 = new Observer("activity2");
        Observer observer3 = new Observer("activity3");

        ObserverOwner owner = new ObserverOwner();
        owner.addObserver(observer1);
        owner.addObserver(observer2);
        owner.addObserver(observer3);

        owner.notifyObservers();
    }

}
