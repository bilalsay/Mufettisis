package dev.demo.Services;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by bilalsay on 02/10/2017.
 */
public class TitleLabel extends Label {

    public TitleLabel(String str) {
        super(str);
        super.setTextFill(Color.web("#08088A"));
        super.setFont(Font.font("System Regular", FontWeight.BOLD, 16));
        super.setStyle("-fx-min-width: 160px");
    }

    public TitleLabel setColor(String color) {
        super.setTextFill(Color.web(color));
        return this;
    }
}
