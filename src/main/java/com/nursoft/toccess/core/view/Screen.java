package com.nursoft.toccess.core.view;

import com.nursoft.toccess.core.interfaces.IScreenFactoryView;
import com.nursoft.toccess.core.models.impl.StorageManager;
import com.nursoft.toccess.views.interfaces.IScreen;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class Screen extends BorderPane implements IScreen, IScreenFactoryView {

    private final StorageManager storageManager;
    private final VBox centerLayer;

    public Screen() {
        super();
        storageManager = StorageManager.getInstance();

        centerLayer = new VBox();
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public VBox getCenterLayer() {
        return centerLayer;
    }
}
