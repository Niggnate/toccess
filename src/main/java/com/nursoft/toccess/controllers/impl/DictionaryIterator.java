package com.nursoft.toccess.controllers.impl;

import com.nursoft.toccess.controllers.Dictionary;
import com.nursoft.toccess.controllers.interfaces.IDictionaryIterator;

import java.util.Iterator;

public class DictionaryIterator<K, V> implements IDictionaryIterator<K, V> {

    private final Dictionary<K, V> dictionary;

    private int nodeIndex;
    private int bucketIndex;

    public DictionaryIterator(Dictionary<K, V> dictionary) {
        this.dictionary = dictionary;
        nodeIndex = 0;
        bucketIndex = START_INDEX;
    }

    private void updateBucketIndex() {
        int updatedBucketIndex = dictionary.getBucketByIndex(bucketIndex);

        if (updatedBucketIndex != bucketIndex) {
            bucketIndex = updatedBucketIndex;
            nodeIndex = START_INDEX;
        } else if (nodeIndex + 1 >= lengthOfBucket()) {
            bucketIndex = dictionary.getBucketByIndex(bucketIndex + 1);
            nodeIndex = START_INDEX;
        }
    }

    private DoubleLinkedList<K, V> getCurrentLinkedList() {
        return dictionary.getBucketsByIndex(bucketIndex);
    }

    private int lengthOfBucket() {
        return getCurrentLinkedList().length();
    }

    private KeyValue<K, V> getNextKeyValue() {
        nodeIndex++;
        return getCurrentLinkedList().getByIndex(nodeIndex);
    }

    private boolean hasNextUnsafe() {
        updateBucketIndex();
        return bucketIndex != START_INDEX;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public KeyValue<K, V> next() {
        return null;
    }
}
