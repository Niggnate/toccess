package com.nursoft.toccess.views.controls;

import com.nursoft.toccess.core.controls.icons.GraphicBuddy;
import com.nursoft.toccess.core.controls.icons.enums.GraphicBuddyIcon;
import com.nursoft.toccess.core.helpers.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class HeaderView extends HBox {

    private final Label appName;
    public HeaderView() {
        super();
        appName = new Label("Toccess");

        buildLayout();
        styles();

    }

    private void styles() {
        setAlignment(Pos.CENTER);
        setTranslateY(50);

        appName.getStyleClass().add("app-name");
        appName.setFont(Font.font ("Verdana", 35));
    }

    private void buildLayout() {

        final ImageView appLogo = new ImageView(new Image(Utils.getAbsolutePath("resources", "assets/images/toccess.png")));
        appLogo.setFitWidth(50);
        appLogo.setFitHeight(50);

        getChildren().addAll(appLogo, appName);
    }

}
