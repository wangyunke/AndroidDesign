package com.i.designpattern.InterfaceAbstract.obd;

/**
 * Created by ykw on 2016/7/13.
 */
public abstract class DataReceiveAbs implements EnvirDetect {
    @Override
    public void onConnect() {} //适配器模式

    @Override
    public void onMutiConnect() {}//适配器模式

    abstract void onReceive();
}
