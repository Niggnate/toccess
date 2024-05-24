package com.nursoft.toccess.core;

import com.nursoft.toccess.core.helpers.Resolutions;
import com.nursoft.toccess.core.helpers.Utils;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ToccessPreLoader extends Preloader {
    private static final StackPane currentWindow = new StackPane();
    private ProgressBar progressBar;
    private Stage preloaderStage;

    private  Resolutions.Bounds16by9 bounds;

    @Override
    public void init() throws Exception {

        bounds = Resolutions.newInstance().getBounds16by9(ToccessLauncher.RATIO);
        buildUI();
    }

    private void buildUI() {

        final VBox preloaderContainer = new VBox(30);
        preloaderContainer.setAlignment(Pos.CENTER);

        final ImageView brand = new ImageView(new Image(Utils.getAbsolutePath("resources", "assets/images/toccess.png")));
        progressBar = new ProgressBar(0.0);
        progressBar.setMinSize((bounds.getWidth() / 2) * .5, 20);

        preloaderContainer.getChildren().addAll(brand, progressBar);
        currentWindow.getChildren().add(preloaderContainer);
        currentWindow.setStyle("-fx-background-color: #fff;");


    }

    @Override
    public void start(Stage stage) throws Exception {
        preloaderStage = stage;

        final Scene scene = new Scene(currentWindow, bounds.getWidth() / 2, bounds.getHeight() / 2);
        scene.getStylesheets().add(Utils.getAbsolutePath("resources", "assets/css/preloader.css"));

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Utils.getAbsolutePath("resources", "assets/images/toccess.png")));
        stage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof ProgressNotification) {
            double progress = ((ProgressNotification) preloaderNotification).getProgress();
            progressBar.setProgress(progress);
        }
    }


    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.close();
        }
    }
}
