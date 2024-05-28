package com.nursoft.toccess.core.interfaces;

import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;
import com.nursoft.toccess.core.enums.ScreenLocator;
import javafx.scene.Node;

public interface IScreenFactory {

    void loadScreen(ScreenLocator locator, Node screen);

    void setScreen(final ScreenLocator locator);

    void removeScreen(final ScreenLocator locator) throws KeyNotFoundException;
}
