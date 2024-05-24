package com.nursoft.toccess.views.controls;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import com.nursoft.toccess.core.helpers.Resolutions;
import com.nursoft.toccess.core.models.AgendaView;
import com.nursoft.toccess.views.interfaces.IControlView;
import com.nursoft.toccess.views.layout.DashtopScreen;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class BodyContentView extends VBox implements IControlView {
    private StackPane stateBox;
    private ActionView actionView;
    private DashtopScreen dashtopScreen;
    private BorderPane emptyBorderPane;

    public BodyContentView(DashtopScreen dashtopScreen, ActionView actionView) {
        super();
        this.dashtopScreen = dashtopScreen;
        this.actionView = actionView;
        stateBox = new StackPane();
        emptyBorderPane = new BorderPane();
        buildLayout();
        cascadeStyling();
        checkAvailability();
    }

    private void checkAvailability() {
        boolean has = !dashtopScreen.getStorageManager().getObservableDataList().isEmpty();
        if (!has) {
            stateBox.getChildren().add(createEmptyBox());
        }

        Button del = actionView.getDeleteSelectedAgendaButton();
        del.setVisible(false);

        dashtopScreen.getStorageManager().getObservableDataViewList().addListener((ListChangeListener<AgendaView>) change -> {
            boolean hasAgenda = false;

            while (change.next()) {
                hasAgenda = !change.getList().isEmpty();
            }

            if (hasAgenda) {
                stateBox.getChildren().remove(createEmptyBox());
            } else {
                stateBox.getChildren().add(createEmptyBox());
            }
        });

        dashtopScreen.getStorageManager().getMainListView().getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
            del.setVisible(nv != null);
        });

    }

    @Override
    public void buildLayout() {
        setAlignment(Pos.CENTER);

        getChildren().add(stateBox);

        stateBox.getChildren().add(dashtopScreen.getStorageManager().getMainListView());

    }

    @Override
    public void cascadeStyling() {
        getStyleClass().add("body-content-view");

        // Size Config
        final Resolutions.Bounds16by9 bounds = Resolutions.newInstance().getBounds16by9(0.8);

        setPrefHeight((bounds.getHeight() * .8) - 110);
    }

    private BorderPane createEmptyBox() {
        final StackPane emptyListIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.FOLDER_OPENED, "rgb(150, 150, 150)", 4, 4);
        final Label emptyListLabel = new Label("Ops! Your list appears to be empty.");
        emptyListLabel.setStyle("-fx-padding: 15 0 15 0; -fx-font-size: 18px;");


        final VBox emptyListContainer = new VBox(20);
        emptyListContainer.getChildren().addAll(emptyListIcon, emptyListLabel);
        emptyListContainer.setAlignment(Pos.CENTER);

        emptyBorderPane.setCenter(emptyListContainer);
        emptyBorderPane.setStyle("-fx-background-color: white;");

        return emptyBorderPane;
    }
}
