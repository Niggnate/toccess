package com.nursoft.toccess.core.impl;

import com.nursoft.toccess.controllers.Dictionary;
import com.nursoft.toccess.core.enums.ScreenLocator;
import com.nursoft.toccess.core.interfaces.IScreenFactory;

import com.nursoft.toccess.core.models.AgendaView;
import com.nursoft.toccess.core.models.impl.StorageManager;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public abstract class AbstractScreenFactory extends StackPane implements IScreenFactory {

    private final Dictionary<ScreenLocator, Node> screens;

    public AbstractScreenFactory() {
        super();
        screens = new Dictionary<>();
        dataViewInitialize();
    }

    private void dataViewInitialize() {

        final StorageManager storageManager = StorageManager.getInstance();
        storageManager.getMainListView().setItems(storageManager.getObservableDataViewList());

        mainListViewInitialize(storageManager.getMainListView());

    }

    private void mainListViewInitialize(ListView<AgendaView> mainListView) {

        // Update list view items
        mainListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(AgendaView agendaView, boolean empty) {
                super.updateItem(agendaView, empty);

                if (empty || agendaView == null) {
                    setGraphic(null);
                } else {
                    setGraphic(agendaView);
                }
            }
        });

        VBox.setVgrow(mainListView, Priority.ALWAYS);
    }


    public Dictionary<ScreenLocator, Node> getScreens() {
        return screens;
    }
}
