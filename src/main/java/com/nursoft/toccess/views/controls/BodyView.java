package com.nursoft.toccess.views.controls;

import com.nursoft.toccess.views.impl.ControlView;
import com.nursoft.toccess.views.interfaces.IControlView;
import com.nursoft.toccess.views.layout.DashtopScreen;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BodyView extends VBox implements IControlView {

    private ActionView actionView;
    private BodyContentView contentView;
    private DashtopScreen dashtopScreen;

    public BodyView(DashtopScreen dashtopScreen) {
        this.dashtopScreen = dashtopScreen;
        // Initialize
        actionView = new ActionView(this);
        contentView = new BodyContentView(dashtopScreen, actionView);

        buildLayout();
    }

    @Override
    public void buildLayout() {

        VBox.setVgrow(contentView, Priority.ALWAYS);

        getChildren().addAll(actionView, contentView);
    }

    @Override
    public void cascadeStyling() {

    }

    public DashtopScreen getDashtopScreen() {
        return dashtopScreen;
    }

    public ActionView getActionView() {
        return actionView;
    }
}
