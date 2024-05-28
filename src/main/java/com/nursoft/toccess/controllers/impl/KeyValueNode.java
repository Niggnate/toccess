package com.nursoft.toccess.controllers.impl;


public class KeyValueNode<K, V> {
    private final K key;
    private final V value;

    private KeyValueNode<K, V> nextNode;
    private KeyValueNode<K, V> previousNode;

    public KeyValueNode(K key, V value) {
        this.key = key;
        this.value = value;

        nextNode = null;
        previousNode = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public KeyValueNode<K, V> getNextNode() {
        return nextNode;
    }

    public void setNextNode(KeyValueNode<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public KeyValueNode<K, V> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(KeyValueNode<K, V> previousNode) {
        this.previousNode = previousNode;
    }

    public KeyValue<K, V> getKeyValue() {
        return new KeyValue<>(key, value);
    }


}
