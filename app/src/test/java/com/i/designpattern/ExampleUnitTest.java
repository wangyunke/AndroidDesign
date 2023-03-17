package com.i.designpattern;

import org.junit.Test;

import static org.junit.Assert.*;

import com.i.designpattern.observer.SubjectObserver;
import com.i.designpattern.observer.SubjectOwner;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

}