package com.i.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtil {

    public void createObject() throws Exception {
        Person person1 = Person.class.newInstance();

        Person person2 = Person.class.getConstructor().newInstance();
        person2.name = "wang";

        Person person3 = (Person) person2.clone();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""));
        out.writeObject(person3);
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(""));
        Person person4 = (Person) input.readObject();

    }
}
