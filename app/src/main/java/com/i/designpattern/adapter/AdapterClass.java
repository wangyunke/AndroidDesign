package com.i.designpattern.adapter;

/**
 * Created by ykw on 2016/6/23.
 */
public abstract class AdapterClass implements IService{
    @Override
    public void methodA() {

    }

    abstract void methodD();//装饰模式
}
