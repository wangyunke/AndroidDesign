package com.i.designpattern.templet;

/**
 * 模板方法模式
 */
public abstract class Car {

   final public void drive(){
        this.start();
        this.stop();
    }

    protected abstract void start();

    protected abstract void stop();

}
