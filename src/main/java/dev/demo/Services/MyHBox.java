package dev.demo.Services;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Created by bilalsay on 02/10/2017.
 */
public class MyHBox extends HBox {

    public MyHBox() {
        super();
    }

    public MyHBox addElements(Node... elements) {
        super.getChildren().addAll(elements);
        return this;
    }
}
