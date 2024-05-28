package com.nursoft.toccess.controllers.impl;

import com.nursoft.toccess.controllers.interfaces.IDoubleLinkedList;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class AbstractDoubleLinkedList<K, V> implements IDoubleLinkedList<K, V> {

    private KeyValueNode<K, V> head;
    private int nodes;

    protected AbstractDoubleLinkedList() {
        this(null, 0);
    }

    protected AbstractDoubleLinkedList(KeyValueNode<K, V> head, int nodes) {
        this.head = head;
        this.nodes = nodes;
    }

    protected void insertFirst(KeyValueNode<K, V> kvn) {
        if (head != null) {
            kvn.setNextNode(head);
            head.setPreviousNode(kvn);
        }

        head = kvn;
        nodes++;
    }

    protected KeyValueNode<K, V> findNodeByKey(K key) {
        KeyValueNode<K, V> kvnPointer = head;
        while (kvnPointer != null) {
            if (kvnPointer.getKey().equals(key)) {
                return kvnPointer;
            }
            kvnPointer = kvnPointer.getNextNode();
        }
        return null;
    }

    protected void removeNode(KeyValueNode<K, V> node) {
        nodes--;
        KeyValueNode<K, V> previousPointer = node.getPreviousNode();
        KeyValueNode<K, V> nextPointer = node.getNextNode();

        if (previousPointer == null) {
            head = node.getNextNode();
        } else if (nextPointer == null) {
            previousPointer.setNextNode(null);
        } else {
            node.setNextNode(null);
            node.setPreviousNode(null);
            previousPointer.setNextNode(nextPointer);
            nextPointer.setPreviousNode(previousPointer);
        }

    }

    protected Stream<KeyValueNode<K, V>> streamNodes() {
        ArrayList<KeyValueNode<K, V>> listOfKeyValueNodes = new ArrayList<>();
        KeyValueNode<K, V> tempPointer = head;
        while (tempPointer != null) {
            listOfKeyValueNodes.add(tempPointer);
            tempPointer = tempPointer.getNextNode();
        }
        return listOfKeyValueNodes.stream();
    }

    public int getNodes() {
        return nodes;
    }

    public KeyValueNode<K, V> getHead() {
        return head;
    }
}
