package com.i.designpattern.rxjava;

/**
 * Created by ykw on 2016/7/14.
 */
public class User {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String age, String name) {
        this.age = age;
        this.name = name;
    }
}
