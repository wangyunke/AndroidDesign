package com.i.designpattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by ykw on 2016/6/27.
 * 观察者模式
 */
public class ObserverOwner implements IObserverOwner {
    private final Vector<IObserver> mList = new Vector<>();

    @Override
    public synchronized void addObserver(IObserver iObserver) {
        if(iObserver != null && !mList.contains(iObserver)){
            mList.add(iObserver);
        }
    }

    @Override
    public synchronized void removeObserver(IObserver iObserver) {
        mList.removeElement(iObserver);
    }

    public void notifyObservers() {
        if (mList.size() > 0) {
            Object[] arrLocal;
            synchronized (this) {
                arrLocal = mList.toArray();
            }

            for (int i = arrLocal.length-1; i>=0; i--) {
                ((IObserver)arrLocal[i]).update();
            }
        }
    }
}
