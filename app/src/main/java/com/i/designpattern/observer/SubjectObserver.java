package com.i.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class SubjectObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
        System.out.println(o.toString() + ":更新自身");
    }
}
