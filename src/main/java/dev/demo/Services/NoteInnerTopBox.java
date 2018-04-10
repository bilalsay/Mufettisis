package dev.demo.Services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import dev.demo.Controller.CrudController;
import dev.demo.Entity.Note;
import dev.demo.Services.SpecialEnums.ColorCategory;
import javafx.fxml.FXMLLoader;
public class NoteInnerTopBox extends TopBox {
	
public NoteInnerTopBox(CrudController controller, Note note) {
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/noteInnerTopBox.fxml"));
	   fxmlLoader.setRoot(this);
	   fxmlLoader.setController(this);
	   try {
	        fxmlLoader.load();
	   } catch (IOException exception) {
	       throw new RuntimeException(exception);
	   }
	   
	   dateLabel.setText((new SimpleDateFormat("dd.MM.yyyy").format(note.getYayinTarih())));
   	   kategoriLabel.setStyle(" -fx-background-color: " + ColorCategory.findById(note.getKategori()).getEnLabel());
	   
	   removeButton.setOnMouseClicked(event -> {
   		try {
   			controller.doRemove(note);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   	});
}
}
