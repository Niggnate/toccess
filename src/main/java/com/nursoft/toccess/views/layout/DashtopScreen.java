package com.nursoft.toccess.views.layout;

import com.nursoft.toccess.core.enums.ScreenLocator;
import com.nursoft.toccess.core.impl.ScreenFactory;
import com.nursoft.toccess.core.models.AgendaView;
import com.nursoft.toccess.core.view.Screen;
import com.nursoft.toccess.views.controls.BodyView;
import com.nursoft.toccess.views.controls.HeaderView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.UUID;

public class DashtopScreen extends Screen {

    private HeaderView headerView;
    private BodyView bodyView;

    public DashtopScreen() {
        super();
        buildLayout();
        cascadeSetters();
        actionEventSetters();
    }

    ScreenFactory factory;
    @Override
    public void setScreenToView(ScreenFactory factory) {
        this.factory = factory;
    }

    @Override
    public String getClassImplID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void buildLayout() {

        headerView = new HeaderView();
        bodyView = new BodyView(this);

        // Set center
        setCenter(getCenterLayer());

        getCenterLayer().getChildren().addAll(headerView, bodyView);


    }

    @Override
    public void cascadeSetters() {
        getStyleClass().add("dashtop");
        headerView.getStyleClass().add("header-view");
    }

    @Override
    public void actionEventSetters() {

        // Action View
        getThisCreateAgendaButton().setOnAction(this::actionEventListeners);
        getThisDeleteButton().setOnAction(this::actionEventListeners);
    }

    public Button getThisDeleteButton() {
        return bodyView.getActionView().getDeleteSelectedAgendaButton();
    }

    public Button getThisCreateAgendaButton() {
        return bodyView.getActionView().getCreateAgendaButton();
    }

    public BodyView getBodyView() {
        return bodyView;
    }

    @Override
    public void actionEventListeners(ActionEvent event) {
        if (event.getSource() == getThisCreateAgendaButton()) {
            factory.setScreen(ScreenLocator.CREATE_AGENDA);
        } else if (event.getSource() == getThisDeleteButton()) {
            deleteSelectedAgenda();
        }
    }

    private void deleteSelectedAgenda() {
        final ObservableList<AgendaView> selected = getStorageManager().getMainListView().getSelectionModel().getSelectedItems();
        for (AgendaView view: selected) {
            String selectedID = view.getAgenda().getId();
            getStorageManager().getObservableDataList().removeIf(item -> item.getId().equals(selectedID));
        }
        getStorageManager().getObservableDataViewList().removeAll(selected);
        getStorageManager().getMainListView().getSelectionModel().clearSelection();
    }
}
