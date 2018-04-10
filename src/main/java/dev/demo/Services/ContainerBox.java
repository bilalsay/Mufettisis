package dev.demo.Services;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ContainerBox extends HBox {

@FXML
public VBox innerContainer;
@FXML
public HBox topBox;
@FXML
public HBox textBox;
@FXML
public Text textData;
public TopBox childOfTopBox;
	
public ContainerBox(TopBox childOfTopBox) {
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/containerBox.fxml"));
	   fxmlLoader.setRoot(this);
	   fxmlLoader.setController(this);
	   try {
	        fxmlLoader.load();
	   } catch (IOException exception) {
	       throw new RuntimeException(exception);
	   }
	   this.childOfTopBox = childOfTopBox;
	   topBox.getChildren().add(childOfTopBox);
	   
}
}
