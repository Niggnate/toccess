package com.nursoft.toccess.core.helpers;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Utils {

    public static String getAbsolutePath(String type, String v) {
        String url = "";
        if (type.equals("java")) {
            url = String.format("src/main/java/com/nursoft/toccess/%s", v);
        } else if (type.equals("resources")) {
            url = String.format("src/main/resources/com/nursoft/toccess/%s", v);
        }

        final Path path = Paths.get(url);
        final File f = new File(path.toUri());
        return String.format("file:///%s", f.getAbsolutePath().replace("\\", "/"));
    }

    public static String transformDate(LocalDate targetDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate, targetDate);
        long days = ChronoUnit.DAYS.between(currentDate, targetDate);

        if (days == 0) {
            return "Due Today";
        } else if (days == 1) {
            return "Due Tomorrow";
        } else if (days == 2) {
            return "Due Day after tomorrow";
        } else if (days >= 7) {
            long weeks = days / 7;
            if (weeks == 1) {
                return String.format("Due in %d week", weeks);
            }
            return String.format("Due in %d weeks", weeks);
        } else {
            return "";
        }
    }

    public static StackPane transformIcons(String category) {
        final double width = 1.2, height = 1.2;
        switch (category) {
            case "Important" -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.CAUTION, "#0096FF", width, height);
            }

            case "School" -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.OPENED_BOOK, "#0096FF", width, height);
            }

            case "Work" -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.BRIEFCASE, "#0096FF", width, height);
            }

            case "Personal" -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.USER, "#0096FF", width, height);
            }

            case "Urgent" -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.OUT_OF_TIME, "#0096FF", width, height);
            }

            default -> {
                return GraphicBuddy.getGraphics(GraphicBuddyIcon.CATEGORY, "#0096FF", width, height);
            }
        }
    }
}
