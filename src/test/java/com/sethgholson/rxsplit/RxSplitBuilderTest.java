package com.sethgholson.rxsplit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class RxSplitBuilderTest {

    String[] VALUES = new String[]{"a", "b", "c"};

    @Test
    public void testSplitBuilderSetsSplitter() throws Exception {
        Splitter testSplitter = new Splitter() {

            @Override
            public String[] split(String source) {
                return VALUES;
            }
        };

        RxSplit rxSplit = RxSplit.newBuilder().splitter(testSplitter).build();
        assertThat(rxSplit.splitter).isEqualTo(testSplitter);
    }


    @Test
    public void testRxSplitBuilderThrowsOnMissingSplitter() throws Exception {
        try {
            RxSplit.newBuilder().build();
            fail("IllegalArgumentException was expected to be thrown.");
        } catch (IllegalArgumentException ex) {
        }
    }
}
