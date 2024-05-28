package com.nursoft.toccess.controllers.impl;

import com.nursoft.toccess.controllers.interfaces.IDictionary;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractDictionary<K, V> implements IDictionary<K, V> {
    private DoubleLinkedList<K, V>[] buckets;
    private static int bucketCapacity = 64;
    protected final Object editLock = new Object();

    public AbstractDictionary() {
        init();
    }

    public AbstractDictionary(DoubleLinkedList<K, V>[] buckets, int capacity) {
        this.buckets = buckets;
        AbstractDictionary.bucketCapacity = capacity;
    }

    @SuppressWarnings("unchecked")
    protected void init() {
        buckets = new DoubleLinkedList[bucketCapacity];
        for (int i = 0; i < bucketCapacity; i++) {
            buckets[i] = new DoubleLinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % bucketCapacity;
    }

    protected DoubleLinkedList<K, V> findByHoldingKey(K key) {
        int index = hash(key);
        return buckets[index];
    }

    protected Stream<DoubleLinkedList<K, V>> bucketsStream() {
        return Arrays.stream(buckets).filter(bucket -> !bucket.isEmpty());
    }
}
