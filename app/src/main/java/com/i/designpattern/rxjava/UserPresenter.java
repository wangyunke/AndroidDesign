package com.i.designpattern.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ykw on 2016/7/14.
 */
public class UserPresenter {
    private UserView mUserView;
    private UserModel mUserModel;

    public UserPresenter(UserView userView) {
        mUserView = userView;
        mUserModel = new UserModel();
    }

    public void getUserDb() {
        mUserView.showLoading();

        Observable<User> mObservable = mUserModel.getUserDb();
        mObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        mUserView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserView.hideLoading();
                    }

                    @Override
                    public void onNext(User user) {
                        mUserView.updateUI(user);
                    }
                });
    }

    public void getNetReq() {
        mUserView.showLoading();
        mUserModel.getNetReq()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Joke>() {
                    @Override
                    public void onCompleted() {
                        mUserView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserView.hideLoading();
                    }

                    @Override
                    public void onNext(Joke joke) {
                        mUserView.updateUI(joke);
                    }
                });
    }
}
