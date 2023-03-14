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

}