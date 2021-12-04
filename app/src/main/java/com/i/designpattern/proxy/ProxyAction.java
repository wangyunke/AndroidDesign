package com.i.designpattern.proxy;

/**
 * Created by ykw on 2016/6/24.
 * 王婆
 */
public class ProxyAction implements IAction{
    IAction action;

    public ProxyAction(IAction action) {
        this.action = action;
    }


    @Override
    public void flyEye() {
        action.flyEye();
    }

    @Override
    public void touchMouth() {
        if(preFactor()) {    //代理模式
            action.touchMouth();
        }

        addTouchEvent();  //装饰模式
    }

    private boolean preFactor() {
        //代理模式的作用及优点
        // 设置访问权限  1、是否有网络   2、是否加载到最后一页
        return false;
    }

    private void addTouchEvent() {
        //装饰模式的作用及优点
        // 功能的加强  锦上添花
    }


}
