package com.nursoft.toccess.controllers.interfaces;

import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;
import com.nursoft.toccess.controllers.impl.DoubleLinkedList;
import com.nursoft.toccess.controllers.impl.KeyValue;

import java.util.List;
import java.util.Set;

public interface IDictionary<K, V> extends Iterable<KeyValue<K, V>> {

    int getBucketByIndex(int index);

    DoubleLinkedList<K, V> getBucketsByIndex(int index);

    void put(K key, V value);

    V get(K key) throws KeyNotFoundException;

    V get(K key, V value);

    V getThreadSafe(K key, V value);

    void remove(K key) throws KeyNotFoundException;

    void clear();

    Set<K> getKeys();

    List<V> getValues();

    List<KeyValue<K, V>> getKeyValue();

    boolean isPresent(K key);



}
