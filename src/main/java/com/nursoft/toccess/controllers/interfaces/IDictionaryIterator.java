package com.nursoft.toccess.controllers.interfaces;

import com.nursoft.toccess.controllers.impl.KeyValue;

import java.util.Iterator;

public interface IDictionaryIterator<K, V> extends Iterator<KeyValue<K, V>> {
    int START_INDEX = -1;
}
