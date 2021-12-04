package com.i.designpattern.proxy;

/**
 * Created by ykw on 2016/6/24.
 */
public class Entity {

    public void test() {
        IAction panJinLian = new RealAction();
        ProxyAction proxyHandler = new ProxyAction(panJinLian);//代理模式

        proxyHandler.flyEye();
        proxyHandler.touchMouth();
    }
}
