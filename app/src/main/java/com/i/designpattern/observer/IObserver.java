package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public interface IObserver {
    void register(IObserver iObserver);
    void unregister(IObserver iObserver);
    void notifyObservers();
}
