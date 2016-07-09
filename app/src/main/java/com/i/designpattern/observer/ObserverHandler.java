package com.i.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykw on 2016/6/27.
 */
public class ObserverHandler {
    List<IWatcher> list=new ArrayList<>();

    public void register(IWatcher watcher){
        list.add(watcher);
    }

    public void unregister(IWatcher watcher){
        list.remove(watcher);
    }

    public void notifyObservers(){
        if(list!=null && list.size()>0) {
            for (IWatcher watcher : list) {
                watcher.update();
            }
        }
    }
}
