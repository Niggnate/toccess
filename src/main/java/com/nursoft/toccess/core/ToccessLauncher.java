package com.nursoft.toccess.core;

import com.nursoft.toccess.core.helpers.Resolutions;
import com.nursoft.toccess.core.helpers.Resolutions.Bounds16by9;
import com.nursoft.toccess.core.helpers.Utils;
import com.nursoft.toccess.core.impl.ScreenFactory;
import com.nursoft.toccess.core.interfaces.IToccessLauncher;
import com.nursoft.toccess.core.models.impl.StorageManager;

import com.nursoft.toccess.views.layout.DashtopScreen;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

/**
 * <p>ToccessMain class extends the application abstract class from javafx</p>
 *
 *
 * @author Prince Khanyile
 * @version 1.1
 *
 *
 * */
public class ToccessLauncher extends Application implements IToccessLauncher {

    private static Stage window;
    private StorageManager storageManager;
    private static ScreenFactory screenFactory;

    private DashtopScreen dashtopScreen;

    @Override
    public void init() throws Exception {
        super.init();
        storageManager = StorageManager.getInstance();
        storageManager.collectRecords();

        // Create width bounds for [width, height]
        final Bounds16by9 bounds = Resolutions.newInstance().getBounds16by9(RATIO);

        screenFactory = new ScreenFactory();
        screenFactory.getStylesheets().clear();
        screenFactory.getStylesheets().add(Utils.getAbsolutePath("resources", "assets/css/app.css"));

        screenFactory.setPrefWidth(bounds.getWidth());
        screenFactory.setPrefHeight(bounds.getHeight());


        dashtopScreen = new DashtopScreen();
        dashtopScreen.setPrefWidth(bounds.getWidth());
        dashtopScreen.setPrefHeight(bounds.getHeight());
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // Create scene
        final Scene scene = createScene(screenFactory.getPrefWidth(), screenFactory.getPrefHeight());

        screenFactory.loadScreen(DASHTOP_LOCATOR, dashtopScreen);

        // Set screen
        screenFactory.setScreen(DASHTOP_LOCATOR);

        // Generate points to center application
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = (int) (centerPoint.x - scene.getWidth() / 2);
        int dy = (int) (centerPoint.y - scene.getHeight() / 2);

        // Stage config
        stage.setTitle("Toccess");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setX(dx);
        stage.setY(dy);
        stage.getIcons().add(new Image(Utils.getAbsolutePath("resources", "assets/images/toccess.png")));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
//        storageManager.storeRecords();
    }

    private static Scene createScene(double width, double height) {
        return new Scene(screenFactory, width, height);
    }


    /**
     * Retrieves the main/current window[stage]
     *
     *
     *<p>This method returns the current window class instance.</p>
     *
     *
     * @return The main window of the application
     */
    public static Stage getWindow() {
        return window;
    }

    @Override
    public void simulateDisplayTasks() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            double progress = (i + 1) / 100.0;
            notifyPreloader(new Preloader.ProgressNotification(progress));
            Thread.sleep(20);
        }
    }
}
