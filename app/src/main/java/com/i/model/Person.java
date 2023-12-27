package com.i.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Cloneable, Serializable {

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    protected java.lang.Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
