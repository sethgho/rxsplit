package com.sethgholson.rxsplit;

import rx.Observable;

/**
 * Strings to streams since 2016.
 *
 * RxSplit gets out of your way and uses {@link Splitter}
 * to let you control the String splitting exactly how you
 * prefer.
 */
public class RxSplit {

    final Splitter splitter;

    private RxSplit(Splitter splitter) {
        this.splitter = splitter;
    }

    /**
     * Translate a {@link String} to a stream of characters.
     * @param source The source {@link String} to be split.
     * @return an {@link Observable} stream of the individual characters
     * in the source String.
     */
    public Observable<String> split(String source) {
        return Observable.from(splitter.split(source));
    }

    public static RxSplitBuilder newBuilder() {
        return new RxSplitBuilder();
    }

    public static class RxSplitBuilder {
        Splitter splitter;

        public RxSplitBuilder() {
        }

        public RxSplitBuilder splitter(Splitter splitter) {
            this.splitter = splitter;
            return this;
        }

        public RxSplit build() {
            if(splitter == null) {
                throw new IllegalArgumentException("You can't split without a splitter.");
            }
            return new RxSplit(splitter);
        }
    }
}
