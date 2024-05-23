package com.nursoft.toccess.core.interfaces;

import com.nursoft.toccess.core.enums.ScreenLocator;

public interface IToccessLauncher {

    ScreenLocator DASHTOP_LOCATOR = ScreenLocator.DASHTOP;
    double RATIO = 0.8;

    void simulateDisplayTasks() throws InterruptedException;
}
