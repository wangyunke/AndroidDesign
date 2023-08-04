package com.i.print;

public class Transfer {

    public static void main(String[] args) {

        try {
            System.out.println("111");
            return;
        } catch (Exception e){
            System.out.println("222");
        } finally {
            System.out.println("333");
        }

        //-------------------------------------



        String value = "aaaa";
        update(value);
        System.out.println(value);

    }

    public static void update(String value){
        value = "bbbb";
        System.out.println("update="+value);
    }

}
