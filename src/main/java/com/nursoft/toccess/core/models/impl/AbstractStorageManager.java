package com.nursoft.toccess.core.models.impl;

import com.nursoft.toccess.core.models.AgendaView;
import com.nursoft.toccess.core.models.interfaces.IStorageManager;

import javafx.scene.control.ListView;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public abstract class AbstractStorageManager implements Serializable, IStorageManager {


    private final DateTimeFormatter formatter;
    private final ListView<AgendaView> mainListView;

    protected AbstractStorageManager() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        mainListView = new ListView<>();
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public ListView<AgendaView> getMainListView() {
        return mainListView;
    }
}
