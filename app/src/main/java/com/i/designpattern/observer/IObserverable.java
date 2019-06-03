package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public interface IObserverable {
    void register(IObserver iObserver);
    void unregister(IObserver iObserver);
    void notifyObservers();
}
