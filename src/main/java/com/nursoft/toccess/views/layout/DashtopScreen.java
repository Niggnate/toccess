package com.nursoft.toccess.views.layout;

import com.nursoft.toccess.core.impl.ScreenFactory;
import com.nursoft.toccess.core.view.Screen;
import com.nursoft.toccess.views.controls.BodyView;
import com.nursoft.toccess.views.controls.HeaderView;
import javafx.application.Platform;
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
        bodyView.getActionView().getCreateAgendaButton().setOnAction(this::actionEventListeners);
        bodyView.getActionView().getDeleteSelectedAgendaButton().setOnAction(this::actionEventListeners);
    }

    public Button getDeleteButton() {
        return bodyView.getActionView().getDeleteSelectedAgendaButton();
    }

    public BodyView getBodyView() {
        return bodyView;
    }

    @Override
    public void actionEventListeners(ActionEvent event) {

    }
}
