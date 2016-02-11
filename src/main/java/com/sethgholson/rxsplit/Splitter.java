package com.sethgholson.rxsplit;

/**
 * An interface {@link RxSplit} will use for turning
 * any String into an array of {@link String}.
 */
public interface Splitter {
    String[] split(String source);
}
