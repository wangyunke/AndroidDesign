package com.i.designpattern.composite;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class FileKillInstance {

    public static void kill() {
        File folder, folder2, folder1;
        folder = new Folder("资料库");
        folder1 = new Folder("文本文件");
        folder2 = new Folder("图像文件");

        File text1, text2;
        text1 = new TextFile("九阴真经.txt");
        text2 = new TextFile("葵花宝典.doc");
        folder1.add(text1);
        folder1.add(text2);

        File image1, image2;
        image1 = new ImageFile("小龙女.jpg");
        image2 = new ImageFile("张无忌.gif");
        folder2.add(image1);
        folder2.add(image2);

        folder.add(folder1);
        folder.add(folder2);

        folder.killVirus();
    }
}
