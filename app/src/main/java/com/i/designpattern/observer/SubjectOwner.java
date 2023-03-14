package com.i.designpattern.observer;

import java.util.Observable;

/**
 * java官方提供的观察者模式
 */
public class SubjectOwner extends Observable {

    public void makeChange(){
        setChanged();
        notifyObservers();
    }

}
