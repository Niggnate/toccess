package com.nursoft.toccess.views.layout;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import com.nursoft.toccess.core.enums.ScreenLocator;
import com.nursoft.toccess.core.helpers.Utils;
import com.nursoft.toccess.core.impl.ScreenFactory;
import com.nursoft.toccess.core.models.Agenda;
import com.nursoft.toccess.core.models.AgendaView;
import com.nursoft.toccess.core.view.Screen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class CreateAgendaScreen extends Screen {

    // Containers
    private final HBox topLayer;
    private final VBox centerLayer;
    private final HBox bottomLayer;
    private final GridPane formControl;

    // Components
    private final Button backToPreviousButton;
    private final TextField titleField;
    private final TextArea descriptionField;
    private final ComboBox<String> categoryField;
    private final DatePicker deadlineField;
    private final Button createAgendaButton;


    public CreateAgendaScreen() {
        super();

        // Containers
        topLayer = new HBox(20);
        centerLayer = new VBox();
        bottomLayer = new HBox();
        formControl = new GridPane(20, 20);

        // Components
        backToPreviousButton = new Button("Back");
        titleField = new TextField();
        descriptionField = new TextArea();

        String[] categoryList = {"--select-option--", "Important", "School", "Work", "Personal", "Urgent"};
        categoryField = new ComboBox<>(FXCollections.observableArrayList(categoryList));
        deadlineField = new DatePicker();
        createAgendaButton = new Button("Save agenda");


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

        // Build main
        setTop(topLayer);
        setCenter(centerLayer);
        setBottom(bottomLayer);

        // Build top layer
        topLayer.getChildren().add(backToPreviousButton);

        final StackPane arrowBack = GraphicBuddy.getGraphics(GraphicBuddyIcon.ARROW_ANGLE_LEFT, "#000000", 1.5, 1.5);
        arrowBack.setStyle("-fx-padding: 10 10 10 10;");

        backToPreviousButton.setGraphic(arrowBack);

        // Build center layer

        // Build form control title bar
        final Label formControlTitle = new Label("Create new agenda");
        formControlTitle.setStyle("-fx-font: 20 aria;");

        final HBox formControlTitleBox = new HBox(formControlTitle);
        formControlTitleBox.setAlignment(Pos.CENTER);
        formControlTitleBox.setStyle("-fx-padding: 30 10 30 10;");

        centerLayer.setAlignment(Pos.CENTER);
        centerLayer.getChildren().addAll(formControlTitleBox, formControl);

        formControl.setAlignment(Pos.CENTER);

        final Label title = new Label("Title");
        title.setStyle("-fx-font: 15 aria;");
        formControl.add(title, 0, 0);
        formControl.add(titleField, 1, 0);

        final Label description = new Label("Description");
        description.setStyle("-fx-font: 15 aria;");
        formControl.add(description, 0, 1);
        formControl.add(descriptionField, 1, 1);

        final Label category = new Label("Category");
        category.setStyle("-fx-font: 15 aria;");
        formControl.add(category, 0, 2);
        formControl.add(categoryField, 1, 2);

        final Label deadline = new Label("Deadline");
        deadline.setStyle("-fx-font: 15 aria;");
        formControl.add(deadline, 0, 3);
        formControl.add(deadlineField, 1, 3);

        // Build bottom layer
        bottomLayer.setAlignment(Pos.CENTER);
        bottomLayer.getChildren().add(createAgendaButton);

        final StackPane createIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.CHECK_CIRCLE, "#ffffff", 1.27, 1.27);
        createIcon.setStyle("-fx-padding: 10 12 10 10;");

        createAgendaButton.setGraphic(createIcon);

    }

    @Override
    public void cascadeSetters() {
        getStyleClass().add("create-agenda-screen");

        topLayer.setStyle("-fx-padding: 0 10 10 0; -fx-border-style: solid; -fx-border-width: 0 0 1 0; -fx-border-color: rgb(230, 230, 230);");
        backToPreviousButton.setStyle("-fx-font: 18 aria; -fx-outline: none; -fx-background-color: #ffffff; -fx-text-fill: #000000;");

        formControl.setStyle("-fx-padding: 10 50 10 50;");
        titleField.setStyle("-fx-padding: 12 12 12 12; -fx-background-color: rgb(240, 240, 240); -fx-font: 14 aria;");

        bottomLayer.setStyle("-fx-padding: 10 50 10 50;");
        createAgendaButton.setStyle("-fx-font: 18 aria; -fx-outline: none; -fx-background-color: #0096FF; -fx-text-fill: #ffffff; -fx-padding: 10 18 10 10;");
    }

    final Agenda tempAgenda = new Agenda();
    @Override
    public void actionEventSetters() {
        backToPreviousButton.setOnAction(this::actionEventListeners);
        createAgendaButton.setOnAction(this::actionEventListeners);

        // State change
        categoryField.valueProperty().addListener((ob, ov, nv) -> {
            if (!nv.equals("--select-option--")) {
                categoryField.setValue(nv);
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Error fields", "Please select a correct category!!");
            }
        });

        final EventHandler<ActionEvent> changeHandler = event -> {
            LocalDate ld = deadlineField.getValue();
            tempAgenda.setDeadline(ld);
        };
        deadlineField.setOnAction(changeHandler);
    }

    @Override
    public void actionEventListeners(ActionEvent event) {
        if (event.getSource() == backToPreviousButton) {
            factory.setScreen(ScreenLocator.DASHTOP);
        } else if (event.getSource() == createAgendaButton) {
            createAndUpdateAgenda();
        }
    }

    private void createAndUpdateAgenda() {
        if (titleField.getText().isEmpty() || descriptionField.getText().isEmpty() || categoryField.getValue() == null || deadlineField.getValue() == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error fields", "Fields cannot be empty");
        } else {

            final Agenda agenda = new Agenda();
            agenda.setTitle(titleField.getText());
            agenda.setDescription(descriptionField.getText());
            agenda.setCategory(categoryField.getValue());
            agenda.setDeadline(tempAgenda.getDeadline());


            final Alert alert = Utils.showAndWaitAlert(Alert.AlertType.CONFIRMATION,"Success", "You have successfully created a new agenda");
            final Optional<ButtonType> optional = alert.showAndWait();

            if (optional.isPresent()) {
                getStorageManager().getObservableDataList().add(agenda);
                getStorageManager().getObservableDataViewList().add(new AgendaView(agenda));

                clearAll();
            }
        }
    }

    private void clearAll() {
        tempAgenda.setDeadline(null);

        titleField.setText("");
        descriptionField.setText("");
        categoryField.setValue("");
        deadlineField.setValue(null);
    }
}
