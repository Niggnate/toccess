package com.nursoft.toccess.controllers.impl;

import java.util.Objects;

public class KeyValue<K, V> {

    private final K key;
    private final V value;

    KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        KeyValue<?, ?> thatKV = (KeyValue<?, ?>) obj;
        return Objects.equals(key, thatKV.key) && Objects.equals(value, thatKV.value);
    }
}
