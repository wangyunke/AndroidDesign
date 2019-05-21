package com.i.designpattern.adapter;

/**
 * Created by ykw on 2016/6/23.
 * 适配器模式 https://blog.csdn.net/zhangjg_blog/article/details/18735243
 */
public class SocketAdapter implements GermanySocketInterface {
    private ChinaSocketInterface iChinaSocket;

    public SocketAdapter(ChinaSocketInterface iChinaSocket) {
        this.iChinaSocket = iChinaSocket;
    }

    @Override
    public void chargeWithTwo() {
        iChinaSocket.chargeWithThree();
    }
}
