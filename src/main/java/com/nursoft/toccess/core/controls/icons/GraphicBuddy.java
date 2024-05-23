package com.nursoft.toccess.core.controls.icons;

import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class GraphicBuddy {

    public static StackPane getGraphics(GraphicBuddyIcon icon, String c, double x, double y) {
        final StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);

        switch (icon) {
            case CALENDAR -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_CALENDAR_PATH, x, y));
                return pane;
            }

            case TRASH_BIN -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_TRASH_BIN_PATH, x, y));
                return pane;
            }

            case CHECK_CIRCLE -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_CHECK_CIRCLE, x, y));
                return pane;
            }

            case PLUS_CIRCLE -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_PLUS_CIRCLE, x, y));
                return pane;
            }

            case ARROW_ANGLE_LEFT -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_ARROW_ANGLE_LEFT, x, y));
                return pane;
            }

            case PEN -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_PEN, x, y));
                return pane;
            }

            case NOTEBOOK -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_NOTEBOOK, x, y));
                return pane;
            }

            case CATEGORY -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_CATEGORY, x, y));
                return pane;
            }

            case CLOCK -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_CLOCK, x, y));
                return pane;
            }

            case FOLDER_OPENED -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_FOLDER_OPENED, x, y));
                return pane;
            }

            case CAUTION -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_CAUTION, x, y));
                return pane;
            }

            case OPENED_BOOK -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_OPENED_BOOK, x, y));
                return pane;
            }

            case BRIEFCASE -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_BRIEFCASE, x, y));
                return pane;
            }

            case USER -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_USER, x, y));
                return pane;
            }

            case OUT_OF_TIME -> {
                pane.getChildren().add(createPath(Paint.valueOf(c), GraphicBuddyReferenceIcon.GRAPHIC_BUDDY_REFERENCE_OUT_OF_TIME, x, y));
                return pane;
            }

            default -> {
                pane.getChildren().removeAll();
                return pane;
            }
        }
    }


    private static SVGPath createPath(Paint fill, String content, double scaleX, double scaleY) {
        final SVGPath path = new SVGPath();
        path.setFillRule(FillRule.EVEN_ODD);
        path.setFill(fill);
        path.setContent(content);
        path.setScaleX(scaleX);
        path.setScaleY(scaleY);
        return path;
    }
}
