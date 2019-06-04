package com.i.designpattern.pubsub;

public interface ISubscriber {
    void update();
    void notifyAllChange();
}