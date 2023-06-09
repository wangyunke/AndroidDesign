package com.i.designpattern.lock;

import android.os.ConditionVariable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ykw on 2016/9/4.
 */
public interface ICommond {
    int a=0;

    Lock iLock=new ReentrantLock();

    ConditionVariable iConditionVariable=new ConditionVariable();

    abstract class AbsInnerClass{}
}

