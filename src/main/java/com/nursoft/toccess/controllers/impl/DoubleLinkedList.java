package com.nursoft.toccess.controllers.impl;

import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;

import java.util.stream.Stream;

public class DoubleLinkedList<K, V> extends AbstractDoubleLinkedList<K, V> {

    public DoubleLinkedList() {
        super();
    }

    public DoubleLinkedList (KeyValueNode<K, V> head, int nodes) {
        super(head, nodes);
    }

    @Override
    public Stream<KeyValue<K, V>> getKeyValues() {
        return streamNodes().map(node -> new KeyValue<>(node.getKey(), node.getValue()));
    }

    @Override
    public Stream<V> getValues() {
        return streamNodes().map(KeyValueNode::getValue);
    }

    @Override
    public Stream<K> getKeys() {
        return streamNodes().map(KeyValueNode::getKey);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public int length() {
        return getNodes();
    }

    @Override
    public KeyValue<K, V> getByIndex(int index) {
        if (index >= length()) return null;

        KeyValueNode<K, V> tempPointer = getHead();
        for (int i = 0; i < index; i++) {
            tempPointer = tempPointer.getNextNode();
        }
        return tempPointer.getKeyValue();
    }

    @Override
    public boolean removeByKey(K key) {
        KeyValueNode<K, V> nodeToDelete = findNodeByKey(key);
        if (nodeToDelete == null)
            return false;

        removeNode(nodeToDelete);
        return true;
    }

    @Override
    public V getByKey(K key) throws KeyNotFoundException {
        KeyValueNode<K, V> node = findNodeByKey(key);
        if (node == null)
            throw new KeyNotFoundException(key.toString());
        return node.getValue();
    }

    @Override
    public V getByKey(K key, V value) {
        KeyValueNode<K, V> node = findNodeByKey(key);
        if (node == null) return value;
        return node.getValue();
    }

    @Override
    public void put(K key, V value) {
        KeyValueNode<K, V> newKeyValueNode = new KeyValueNode<>(key, value);
        removeByKey(key);
        insertFirst(newKeyValueNode);
    }
}
