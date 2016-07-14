package com.i.designpattern.rxjava;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.i.designpattern.net.IHttpResult;
import com.i.designpattern.net.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ykw on 2016/7/14.
 */
public class UserModel {
    private int autoInt=1;

    public Observable<User> getUserDb(){
        Observable<User> userObservable=Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                SystemClock.sleep(2000);
                User user = new User("王智"+autoInt++,autoInt+++"");
                if(user==null){
                    subscriber.onError(new Exception("user==null"));
                }else{
                    subscriber.onNext(user);
                }
                subscriber.onCompleted();
            }
        });
        return userObservable;
    }

    public Observable<Joke> getNetReq(){
        return Observable.create(new Observable.OnSubscribe<Joke>() {
            @Override
            public void call(Subscriber<? super Joke> subscriber) {
                getNetData(subscriber);
            }
        });
    }

    public void getNetData(final Subscriber<? super Joke> subscriber){
        try {
            OkHttpUtil.execGet("http://api.1-blog.com/biz/bizserver/xiaohua/list.do?size="+autoInt++,
                    new IHttpResult() {
                        @Override
                        public void onFailure() {
                            subscriber.onError(new Exception(""));
                        }

                        @Override
                        public void onSucess(String response) {
                            try {
                                JSONObject result=new JSONObject(response);
                                JSONObject resultObj= (JSONObject) result.getJSONArray("detail").get(autoInt-2);

                                Gson gson=new Gson();
                                Joke joke=gson.fromJson(resultObj.toString(),Joke.class);
                                subscriber.onNext(joke);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                subscriber.onError(new Exception(""));
                            }
                            subscriber.onCompleted();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
