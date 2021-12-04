package com.i.designpattern.proxy;

/**
 * Created by ykw on 2016/6/24.
 */
public class RealAction implements IAction{

    @Override
    public void flyEye() {
        System.out.println("抛媚眼");
    }

    @Override
    public void touchMouth() {
        System.out.println("羞羞");
    }
}
