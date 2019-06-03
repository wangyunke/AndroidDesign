package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public class CaseInstance {

    public static void use() {
        Observer observer1 = new Observer("activity1");
        Observer observer2 = new Observer("activity2");
        Observer observer3 = new Observer("activity3");

        Observerable observerable = new Observerable();
        observerable.register(observer1);
        observerable.register(observer2);
        observerable.register(observer3);

        observerable.notifyObservers();
    }

}
