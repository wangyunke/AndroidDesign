package com.i.designpattern.net;

public interface IHttpResult {
	void onFailure();
	void onSucess(String response);
}
