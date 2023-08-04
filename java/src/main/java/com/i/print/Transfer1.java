package com.i.print;

public class Transfer1 {

    public static void main(String[] args) {

        User user=new User(18);

        modify(user);

        System.out.println(user.getAge());
    }

    public static void modify(User user){
        user=new User(20);
        System.out.println(user.getAge());
    }

}
