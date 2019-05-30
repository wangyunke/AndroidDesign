package com.i.designpattern.composite;

import java.util.ArrayList;

/**
 * 组合模式
 */
public class Folder extends File {
    private String name;
    private ArrayList<File> folderList = new ArrayList<File>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(File f) {
        folderList.add(f);
    }

    public void killVirus() {
        System.out.println("****对文件夹'" + name + "'进行杀毒");

        for (Object obj : folderList) {
            ((File) obj).killVirus();
        }
    }

}
