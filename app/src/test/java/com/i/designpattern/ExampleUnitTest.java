package com.i.designpattern;

import org.junit.Test;

import static org.junit.Assert.*;

import com.i.designpattern.observer.SubjectObserver;
import com.i.designpattern.observer.SubjectOwner;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void javaObserver() throws Exception {
        SubjectObserver observer=new SubjectObserver();
        SubjectOwner owner=new SubjectOwner();
        owner.addObserver(observer);
        owner.makeChange();
    }

    /**
     * 二进制：相同为0，不同为1
     * 十进制：相同为0，不同再计算，0^数=数
     */
    @Test
    public void numOr() {
        int value = 5^5;
        int value1 = 0^15;
        int value2 = 6^5;
        System.out.println("5^5="+value);
        System.out.println("0^15="+value1);
        System.out.println("6^5="+value2);
        System.out.println("6^5^7^19^5^7^6^19^199="+(6^5^7^19^5^7^6^19^199));
    }

    @Test
    public void aa() {
        String name = "origin_01-09-15_57_21.pcm";
//        Pattern pattern = Pattern.compile("origin_\\w+\\.pcm");
        Pattern pattern = Pattern.compile("origin_.+\\.pcm");
        Matcher matcher = pattern.matcher(name);
        System.out.println(matcher.matches()+"--------234---");
    }

    @Test
    public void matchRule() {
        String RES_RULE = "speech-.*";
        String origin = "speech-usb-audi-hcp3-48-v25072116-20250721175653";
        Pattern pattern = Pattern.compile(RES_RULE);
        Matcher matcher = pattern.matcher(origin);
        if (matcher.matches()) {
            System.out.println("match success");
        } else {
            System.out.println("match fail");
        }
    }

    @Test
    public void threadRun() {
        new Thread(() -> {
            System.out.println("11111111");

            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("2222");
            }).start();

            System.out.println("33333");

        }).start();
        System.out.println("4444");
    }

}