package com.i.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykw on 2016/6/27.
 * 观察者模式
 */
public class Observerable implements IObserverable {
    List<IObserver> list = new ArrayList<>();

    @Override
    public void register(IObserver iObserver) {
        list.add(iObserver);
    }

    @Override
    public void unregister(IObserver iObserver) {
        list.remove(iObserver);
    }

    public void notifyObservers() {
        if (list != null && list.size() > 0) {
            for (IObserver watcher : list) {
                watcher.update();
            }
        }
    }
}
