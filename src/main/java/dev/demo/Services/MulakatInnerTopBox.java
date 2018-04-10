package dev.demo.Services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import dev.demo.Controller.CrudController;
import dev.demo.Entity.Mulakat;
import dev.demo.Services.SpecialEnums.ColorCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MulakatInnerTopBox extends TopBox {
	
public MulakatInnerTopBox(CrudController controller, Mulakat mulakat) {
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mulakatInnerTopBox.fxml"));
	   fxmlLoader.setRoot(this);
	   fxmlLoader.setController(this);
	   try {
	        fxmlLoader.load();
	   } catch (IOException exception) {
	       throw new RuntimeException(exception);
	   }
	   
	   dateLabel.setText((new SimpleDateFormat("dd.MM.yyyy").format(mulakat.getYayinTarih())));
   	   kategoriLabel.setStyle(" -fx-background-color: " + ColorCategory.findById(mulakat.getKategori()).getEnLabel());

	   generateStyle(mulakat);
	   removeButton.setOnMouseClicked(event -> {
	   		try {
	   			controller.doRemove(mulakat);
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
   		});

		okButton.setOnMouseClicked(event -> {
			try {
				controller.doUpdate(mulakat, null);
				generateStyle(mulakat);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void generateStyle(Mulakat mulakat) {
		if (mulakat.getDurum() == 0) {
			okButton.getStyleClass().clear();
			okButton.getStyleClass().add("notOk");
		} else {
			okButton.getStyleClass().clear();
			okButton.getStyleClass().add("ok");
		}
	}
}
