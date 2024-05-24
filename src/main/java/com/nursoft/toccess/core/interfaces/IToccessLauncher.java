package com.nursoft.toccess.core.interfaces;

import com.nursoft.toccess.core.enums.ScreenLocator;

public interface IToccessLauncher {

    ScreenLocator DASHTOP_LOCATOR = ScreenLocator.DASHTOP;
    ScreenLocator CREATE_AGENDA_LOCATOR = ScreenLocator.CREATE_AGENDA;
    double RATIO = 0.8;

    void simulateDisplayTasks() throws InterruptedException;
}
