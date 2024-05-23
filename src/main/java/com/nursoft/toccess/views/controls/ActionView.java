package com.nursoft.toccess.views.controls;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import com.nursoft.toccess.views.impl.ControlView;
import com.nursoft.toccess.views.interfaces.IControlView;
import com.nursoft.toccess.views.layout.DashtopScreen;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class ActionView extends HBox implements IControlView {

    private BodyView bodyView;

    private HBox leftLayer;
    private HBox rightLayer;

    // Components
    private Button createAgendaButton;
    private Button deleteSelectedAgendaButton;

    public ActionView(BodyView bodyView) {
        this.bodyView = bodyView;
        // Initialize
        leftLayer = new HBox(20);
        rightLayer = new HBox(20);


        buildLayout();
        cascadeStyling();
    }

    @Override
    public void buildLayout() {


        getChildren().addAll(leftLayer, rightLayer);


        // Build left layer
        final StackPane calendarIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.CALENDAR, "#000000", 1.5, 1.5);
        calendarIcon.setStyle("-fx-padding: 10 10 10 10;");

        final Label leftLayerTitle = new Label("Agendas");
        leftLayerTitle.setFont(Font.font ("Verdana", 16));

        leftLayer.getChildren().addAll(calendarIcon, leftLayerTitle);



        // Build right layer
        HBox.setHgrow(rightLayer, Priority.ALWAYS);

        createAgendaButton = new Button("Create agenda");
        deleteSelectedAgendaButton = new Button();

        rightLayer.getChildren().addAll(deleteSelectedAgendaButton, createAgendaButton);

        final StackPane deleteIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.TRASH_BIN, "#ffffff", 1.28, 1.28);
        deleteIcon.setStyle("-fx-padding: 10 12 10 10;");
        deleteSelectedAgendaButton.setGraphic(deleteIcon);

        final StackPane createIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.PLUS_CIRCLE, "#ffffff", 1.28, 1.28);
        createIcon.setStyle("-fx-padding: 10 12 10 0;");
        createAgendaButton.setGraphic(createIcon);
    }

    @Override
    public void cascadeStyling() {
        getStyleClass().add("action-view");
        leftLayer.getStyleClass().add("left-layer");
        rightLayer.getStyleClass().add("right-layer");
    }

    public Button getCreateAgendaButton() {
        return createAgendaButton;
    }

    public Button getDeleteSelectedAgendaButton() {
        return deleteSelectedAgendaButton;
    }
}
