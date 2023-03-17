package com.i.Utils;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Cloneable, Serializable {

    String name;
    int age;

    @NonNull
    @Override
    protected java.lang.Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
