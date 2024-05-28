package com.nursoft.toccess.controllers;

import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;
import com.nursoft.toccess.controllers.impl.AbstractDictionary;
import com.nursoft.toccess.controllers.impl.DictionaryIterator;
import com.nursoft.toccess.controllers.impl.DoubleLinkedList;
import com.nursoft.toccess.controllers.impl.KeyValue;
import com.nursoft.toccess.controllers.interfaces.IDictionaryIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary<K, V> extends AbstractDictionary<K, V> {

    private DoubleLinkedList<K, V>[] buckets;
    private final Object editLock = new Object();
    private int capacity;

    public Dictionary() {
        super();
    }

    public Dictionary(DoubleLinkedList<K, V>[] buckets, int capacity) {
        super(buckets, capacity);
        this.buckets = buckets;
        this.capacity = capacity;
    }

    @Override
    public int getBucketByIndex(int index) {
        if (index >= capacity)
            return IDictionaryIterator.START_INDEX;
        int i = index;
        while (i < capacity && buckets[i].isEmpty())
            i++;
        return i == capacity ? IDictionaryIterator.START_INDEX : i;
    }

    @Override
    public DoubleLinkedList<K, V> getBucketsByIndex(int index) {
        return buckets[index];
    }

    @Override
    public void put(K key, V value) {
        synchronized (editLock) {
            DoubleLinkedList<K, V> list = findByHoldingKey(key);
            list.put(key, value);
        }
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        return findByHoldingKey(key).getByKey(key);
    }

    @Override
    public V get(K key, V value) {
        return findByHoldingKey(key).getByKey(key, value);
    }

    @Override
    public V getThreadSafe(K key, V value) {
        synchronized (editLock) {
            return findByHoldingKey(key).getByKey(key, value);
        }
    }

    @Override
    public void remove(K key) throws KeyNotFoundException {
        synchronized (editLock) {
            DoubleLinkedList<K, V> listContainingNodeToRemove = findByHoldingKey(key);
            if (!listContainingNodeToRemove.removeByKey(key))
                throw new KeyNotFoundException(key.toString());
        }
    }

    @Override
    public void clear() {
        synchronized (editLock) {
            init();
            System.gc();
        }
    }

    @Override
    public Set<K> getKeys() {
        return bucketsStream().flatMap(DoubleLinkedList::getKeys).collect(Collectors.toSet());
    }

    @Override
    public List<V> getValues() {
        return bucketsStream().flatMap(DoubleLinkedList::getValues).collect(Collectors.toList());
    }

    @Override
    public List<KeyValue<K, V>> getKeyValue() {
        return bucketsStream().flatMap(DoubleLinkedList::getKeyValues).collect(Collectors.toList());
    }

    @Override
    public boolean isPresent(K key) {
        return !findByHoldingKey(key).isEmpty();
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return new DictionaryIterator<>(this);
    }
}
