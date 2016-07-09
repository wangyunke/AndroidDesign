package com.i.designpattern.observer;

/**
 * Created by ykw on 2016/6/27.
 */
public class WatchTarget { //观察目标
    Watcher watcher=new Watcher(); //指给谁来观察
    ObserverHandler handler=new ObserverHandler();

    public WatchTarget() {
        handler.register(watcher);
    }

    public void onDestory(){
        handler.unregister(watcher);
    }

    public void changeData(){
        handler.notifyObservers();
    }

}
