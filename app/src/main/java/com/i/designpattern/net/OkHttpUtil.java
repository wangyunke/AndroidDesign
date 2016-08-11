package com.i.designpattern.net;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final OkHttpClient client = new OkHttpClient();

    static {
        client.setConnectTimeout(10, TimeUnit.SECONDS);
    }

    public static void execGet(String url, final IHttpResult httpResult)
            throws IOException {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                arg1.printStackTrace();
                httpResult.onFailure();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        httpResult.onSucess(response.body().string());
                    } else {
                        httpResult.onFailure();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void execPost(String url, RequestBody formBody, final IHttpResult httpResult) throws IOException {
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                arg1.printStackTrace();
                httpResult.onFailure();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        httpResult.onSucess(response.body().string());
                    } else {
                        httpResult.onFailure();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
