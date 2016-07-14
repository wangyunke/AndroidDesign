package com.i.designpattern.rxjava;

/**
 * Created by ykw on 2016/7/14.
 */
public interface UserView {
    void updateUI(User user);
    void updateUI(Joke joke);
    void showLoading();
    void hideLoading();
}
