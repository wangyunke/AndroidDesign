package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public class Observer implements IObserver {
    private String name;

    public Observer(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + ":更新自身");
    }
}
