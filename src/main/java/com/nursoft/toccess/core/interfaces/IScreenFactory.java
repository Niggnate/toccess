package com.nursoft.toccess.core.interfaces;

import com.nursoft.toccess.core.enums.ScreenLocator;
import javafx.scene.Node;

public interface IScreenFactory {

    void loadScreen(ScreenLocator locator, Node screen);

    void setScreen(final ScreenLocator locator);

    boolean removeScreen(final ScreenLocator locator);
}
