package com.nursoft.toccess.core.helpers;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.Serializable;

public class Resolutions implements Serializable {

    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static Resolutions instance = null;

    public static Resolutions newInstance() {
        if (instance == null) {
            instance = new Resolutions();
        }
        return instance;
    }

    private Resolutions() {}

    public double getWidth() {
        return SCREEN_BOUNDS.getWidth();
    }

    public double getHeight() {
        return SCREEN_BOUNDS.getHeight();
    }

    public Bounds16by9 getBounds16by9(double ratio) {
        return new Bounds16by9(this, ratio);
    }

    // Inner class 16by9 ratio
    public static class Bounds16by9 {

        private final double ratio;
        private final Resolutions resolutions;

        public Bounds16by9(Resolutions resolutions, double ratio) {
            this.resolutions = resolutions;
            this.ratio = ratio;
        }

        public double getWidth() {
            final double defaultRatio = 0.5;
            if (ratio  == 0.0 || ratio < 0.4) {
                return resolutions.getWidth() * defaultRatio;
            }
            return resolutions.getWidth() * ratio;
        }

        public double getHeight() {
            return (getWidth() / 15) * 9;
        }

    }
}
