package com.nursoft.toccess.controllers.interfaces;

import com.nursoft.toccess.controllers.impl.KeyValue;
import com.nursoft.toccess.controllers.impl.KeyValueNode;
import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;

import java.util.stream.Stream;

public interface IDoubleLinkedList<K, V> {
    Stream<KeyValue<K, V>> getKeyValues();

    Stream<V> getValues();

    Stream<K> getKeys();

    boolean isEmpty();

    int length();

    KeyValue<K, V> getByIndex(int index);

    boolean removeByKey(K key);

    V getByKey(K key) throws KeyNotFoundException;

    V getByKey(K key, V value);

    void put(K key, V value);
}
