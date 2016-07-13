package com.i.designpattern.InterfaceAbstract.dao;

/**
 * Created by ykw on 2016/7/13.
 * 公共的接口方法
 */
public interface IBaseDao<T> {
    void add(T t);
    void delete(int id);
    void update(T t);
}
