package com.nursoft.toccess.core.models;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import com.nursoft.toccess.core.helpers.Resolutions;
import com.nursoft.toccess.core.helpers.Utils;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class AgendaView extends HBox {

    private static final double RATIO = 0.8;
    private static final int PADDING_SIZE = 30;
    private static final double NUMBER_OF_CHILDREN = 4;

    private final Agenda agenda;

    // containers
    private final HBox titleContainer;
    private final HBox descriptionContainer;
    private final HBox categoryContainer;
    private final HBox deadlineContainer;

    // Components
    private final Label title;
    private final Label description;
    private final Label category;
    private final Label deadline;


    public AgendaView(Agenda agenda) {
        this.agenda = agenda;

        // Containers
        titleContainer = new HBox(10);
        descriptionContainer = new HBox();
        categoryContainer = new HBox(10);
        deadlineContainer = new HBox(10);

        // Components
        title = new Label();
        description = new Label();
        category = new Label();
        deadline = new Label();

        buildUI();
        cascadeStyling();

    }

    private void buildUI() {
        // Setters
        title.setText(agenda.getTitle());
        description.setText(agenda.getDescription());
        category.setText(agenda.getCategory());
        deadline.setText(Utils.transformDate(agenda.getDeadline()));

        // Size init
        double size_each = (Resolutions.newInstance().getBounds16by9(RATIO).getWidth() / NUMBER_OF_CHILDREN) - PADDING_SIZE;
        titleContainer.setPrefWidth(size_each);
        descriptionContainer.setPrefWidth(size_each);
        categoryContainer.setPrefWidth(size_each);
        deadlineContainer.setPrefWidth(size_each);

        getChildren().addAll(titleContainer, descriptionContainer, categoryContainer, deadlineContainer);


        // Containers init
        final StackPane notebookIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.NOTEBOOK, "#0096FF", 1.12, 1.12);
        titleContainer.getChildren().addAll(notebookIcon, title);
        titleContainer.setStyle("-fx-padding: 20px; -fx-text-overrun: ellipsis;");

        descriptionContainer.getChildren().add(description);
        descriptionContainer.setStyle("-fx-padding: 20px; -fx-text-overrun: ellipsis;");

        categoryContainer.getChildren().addAll(Utils.transformIcons(agenda.getCategory()), category);
        categoryContainer.setStyle("-fx-padding: 20px; -fx-text-overrun: ellipsis;");

        final StackPane clockIcon = GraphicBuddy.getGraphics(GraphicBuddyIcon.CLOCK, "#0096FF", 1.12, 1.12);
        deadlineContainer.getChildren().addAll(clockIcon, deadline);
        deadlineContainer.setStyle("-fx-padding: 20px; -fx-text-overrun: ellipsis;");
    }

    private void cascadeStyling() {
        title.setStyle("-fx-font: 13 aria;");
        description.setStyle("-fx-font: 13 aria;");
        category.setStyle("-fx-font: 13 aria;");
        deadline.setStyle("-fx-font: 13 aria;");
    }

    public Agenda getAgenda() {
        return agenda;
    }
}
