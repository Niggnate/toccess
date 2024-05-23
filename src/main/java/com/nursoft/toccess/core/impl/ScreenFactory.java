package com.nursoft.toccess.core.impl;

import com.nursoft.toccess.core.enums.ScreenLocator;
import com.nursoft.toccess.core.interfaces.IScreenFactoryView;
import javafx.scene.Node;

public class ScreenFactory extends AbstractScreenFactory {

    public ScreenFactory() {
        super();
    }

    public void addScreen(ScreenLocator locator, Node screen) {
        getScreens().put(locator, screen);
    }

    public Node getScreen(ScreenLocator locator) {
        return getScreens().get(locator);
    }


    @Override
    public void loadScreen(ScreenLocator locator, Node screen) {
        boolean screenLoaded = false;

        try {
            final IScreenFactoryView factoryView = (IScreenFactoryView) screen;
            factoryView.setScreenToView(this);

            // Set each screen unique Id
            screen.setId(factoryView.getClassImplID());

            // Add screen to the list of screens
            addScreen(locator, screen);

            screenLoaded = true;
        } catch (Exception e) {
            System.out.printf("Screen with [id:%s] failed to load.\nReason: %s", screen.getId(), e.getMessage());
        }
    }

    @Override
    public void setScreen(final ScreenLocator locator) {
        boolean screenSet = false;

        if (getScreens().get(locator) != null) {
            if (getChildren().isEmpty()) {
                getChildren().add(getScreens().get(locator));
            } else {
                getChildren().removeFirst();
                getChildren().addFirst(getScreens().get(locator));
            }
            screenSet = true;
        } else {
            System.out.println("Something went wrong when viewing the screen!!!");
        }
    }

    @Override
    public boolean removeScreen(final ScreenLocator locator) {
        if (getScreens().remove(locator) == null) {
            System.out.println("Ops!!! It seems that screen does not exits.");
            return false;
        }
        return true;
    }
}
