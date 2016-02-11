package com.sethgholson.rxsplit;

import org.junit.Test;
import rx.observers.TestSubscriber;

public class RxSplitTest {
    String[] VALUES = new String[]{"a", "b", "c"};

    @Test
    public void testRxSplitUsesSplitter() throws Exception {
        Splitter testSplitter = new Splitter() {

            @Override
            public String[] split(String source) {
                return VALUES;
            }
        };

        RxSplit rxSplit = RxSplit.newBuilder().splitter(testSplitter).build();
        TestSubscriber<String> ts = TestSubscriber.create();
        rxSplit.split(String.join("", VALUES)).subscribe(ts);
        ts.assertValues(VALUES);

    }

    @Test
    public void testRxSplitDoesntUseAnotherSplitter() throws Exception {
        Splitter correctSplitter = new Splitter() {

            @Override
            public String[] split(String source) {
                return new String[0];
            }
        };

        String[] INCORRECT_VALUES = new String[]{"x", "y", "z"};
        Splitter incorrectSplitter = new Splitter() {

            @Override
            public String[] split(String source) {
                return INCORRECT_VALUES;
            }
        };

        RxSplit rxSplit = RxSplit.newBuilder().splitter(correctSplitter).build();
        TestSubscriber<String> ts = TestSubscriber.create();
        rxSplit.split(String.join("", VALUES)).subscribe(ts);
        ts.assertNoValues();
    }

}
