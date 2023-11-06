package com.i.anr;

//    Looper.class
//    private Printer mLogging;
//    private static boolean loopOnce(final Looper me, final long ident, final int thresholdOverride) {
//        // ....
//
//        for (;;) {
//            // ...
//
//            // This must be in a local variable, in case a UI event sets the logger
//            final Printer logging = me.mLogging;
//            if (logging != null) {
//                logging.println(">>>>> Dispatching to " + msg.target + " " +
//                        msg.callback + ": " + msg.what);
//            }
//
//            // ...
//            msg.target.dispatchMessage(msg);
//            // ...
//
//            if (logging != null) {
//                logging.println("<<<<< Finished to " + msg.target + " " + msg.callback);
//            }
//            // ...
//        }
//    }
//
//    方案：替换Looper的Printer对象, 使用方式如下：
//    Looper.getMainLooper().setMessageLogging(new LooperPrinter());

import android.util.Log;
import android.util.Printer;

public class LooperPrinter implements Printer {
    private boolean isStartedPrinting;
    private long mStartTimeMillis;

    @Override
    public void println(String x) {
        if (!isStartedPrinting) {
            mStartTimeMillis = System.currentTimeMillis();
            isStartedPrinting = true;
        } else {
            final long endTime = System.currentTimeMillis();
            isStartedPrinting = false;

            if (isAnrBlock(endTime)) {
                Log.e("LooperPrinter", "发生anr, 收集日志");
            }
        }
    }

    private boolean isAnrBlock(long endTime) {
        return endTime - mStartTimeMillis > 5;
    }

}
