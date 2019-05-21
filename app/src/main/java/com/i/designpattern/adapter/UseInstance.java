package com.i.designpattern.adapter;

/**
 * Created by ykw on 2016/6/23.
 */
public class UseInstance {

    public void charge() {
        ChinaSocketInterface chinaSocket = new ChinaSocket(); //只有国标

        SocketAdapter adapter = new SocketAdapter(chinaSocket); //将国标在适配器中转为德标adapter
        adapter.chargeWithTwo();  //用德标adapter 为chinaSocket充电
    }
}
