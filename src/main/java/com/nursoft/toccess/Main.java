package com.nursoft.toccess;

import com.nursoft.toccess.controllers.exceptions.KeyNotFoundException;
import com.nursoft.toccess.core.ToccessLauncher;
import com.nursoft.toccess.core.ToccessPreLoader;
import com.nursoft.toccess.controllers.Dictionary;

public class Main extends ToccessLauncher {

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", ToccessPreLoader.class.getName());
        launch(Main.class, args);
    }
}
