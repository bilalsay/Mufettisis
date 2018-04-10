package dev.demo.Services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import dev.demo.Controller.CrudController;
import dev.demo.Entity.Islem;
import dev.demo.Services.SpecialEnums.ColorCategory;
import javafx.fxml.FXMLLoader;

public class IslemInnerTopBox extends TopBox {
	
public IslemInnerTopBox(CrudController controller, Islem islem) {
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/islemInnerTopBox.fxml"));
	   fxmlLoader.setRoot(this);
	   fxmlLoader.setController(this);
	   try {
	        fxmlLoader.load();
	   } catch (IOException exception) {
	       throw new RuntimeException(exception);
	   }
	   
	   dateLabel.setText((islem.getDurum() == 1 ? "Bitiş: " + new SimpleDateFormat("dd.MM.yyyy").format(islem.getTamamTarih()) : "Eklenme: " + new SimpleDateFormat("dd.MM.yyyy").format(islem.getYayinTarih())));
	   kategoriLabel.setStyle(" -fx-background-color: " + ColorCategory.findById(islem.getKategori()).getEnLabel());
	   okButton.getStyleClass().clear();
	   okButton.getStyleClass().add("ok");
	   if (islem.getDurum() == 0) {
		   okButton.getStyleClass().clear();
		   okButton.getStyleClass().add("notOk");
	   }
	   
	   removeButton.setOnMouseClicked(event -> {
   		try {
   			controller.doRemove(islem);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   	   });
	   
	   okButton.setOnMouseClicked(event -> {
		   try {
			   controller.doUpdate(islem, null);
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   });
	   
}
}
