package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public interface IObserverOwner {
    void addObserver(IObserver iObserver);

    void removeObserver(IObserver iObserver);

    void notifyObservers();
}