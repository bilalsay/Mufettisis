package dev.demo.Controller;

import dev.demo.Main;
import dev.demo.Dao.IsemriRepository;
import dev.demo.Dao.NoteRepository;
import dev.demo.Entity.AncestorEntity;
import dev.demo.Entity.Isemri;
import dev.demo.Entity.Islem;
import dev.demo.Entity.Note;
import dev.demo.Services.ContainerBox;
import dev.demo.Services.EventGenerator;
import dev.demo.Services.Notifier;
import dev.demo.Services.ObjectInitializer;
import dev.demo.Services.OptionalParamter;
import dev.demo.Services.ViewDataGenerator;
import dev.demo.Services.SpecialEnums.ColorCategory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteController extends CrudController {
	
	/* List Page */
	@FXML
	public VBox extendedFormForScrollingData;
    
    /* Add Page */
    @FXML
	public ComboBox<ColorCategory> kategoriCB;
	@FXML
	public TextArea icerikTA;
	
    @Autowired
    NoteRepository noteRepository;
    
    @Autowired
	IsemriRepository isEmriRepository;

	@Override
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameter) throws Exception {
		Isemri isemri = (Isemri) entity;
		List<Note> noteList = null;
		String searchString = null;
		ColorCategory category = null;
		if (optionalParameter != null) {
			category = optionalParameter.<ColorCategory>getParamter2();
			searchString = optionalParameter.<String>getParamter3();
		}
		noteList = getCorrectList(isemri, category, searchString);
		listPageByParameter(isemri, noteList);	
		EventGenerator.generateNoteEvents(this, isemri, category, searchString);
	}
	
	public void listPageByParameter(Isemri isemri, List<Note> noteList) throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource("/note_list.fxml"));
		ScrollPane noteListView = contentLoader.load();
		main.mainLayout.setCenter(noteListView);
		title.setText("Notlar \n" + isemri.getMukellef().getUnvan() + "\n " + isemri.getIsemriNo());
		if (!noteList.isEmpty()) {
			ObservableList<ContainerBox> notes = ViewDataGenerator.noteDataGenerate(this, noteList);
			extendedFormForScrollingData.getChildren().addAll(notes);
		}
	}
	
	private List<Note> getCorrectList(Isemri isemri, ColorCategory category, String searchString) {
		List<Note> noteList = noteRepository.findByIsemriOrderByYayinTarihAsc(isemri);
		if (category != null && searchString != null) {
			// kategori icinde arama
			noteList = noteRepository.findByIsemriAndKategoriAndIcerikContainingOrderByYayinTarihAsc(isemri,category.getId(), searchString);
		} else if(category != null && searchString == null) {
			// sadece kategori
			noteList = noteRepository.findByIsemriAndKategoriOrderByYayinTarihAsc(isemri,category.getId());
		} else if(category == null && searchString != null) {
			// sadece arama
			noteList = noteRepository.findByIsemriAndIcerikContainingOrderByYayinTarihAsc(isemri, searchString);
		}
		return noteList;
	}
	
	@Override
	public void doAdd(AncestorEntity entity) {
		Isemri isemri = (Isemri) entity;
		postingChecker.setAddingFlag(true);
		boolean textFieldCheck = postingChecker.emptyCheck(icerikTA);
		boolean comboCheck = postingChecker.emptyCheck(kategoriCB);
		if ((textFieldCheck && comboCheck) || postingChecker.isSpecialConditionFlag()) {
			postingChecker.setSpecialConditionFlag(false);
			isemri = isEmriRepository.findOne(isemri.getId());
			if (isemri != null) {
				Note note = new Note();
				note.setIsemri(isemri);
				ObjectInitializer.noteInitialize(this, note);
				note = noteRepository.save(note);
				if (note != null) {
					try {
						extendedFormForScrollingData.getChildren().add(ViewDataGenerator.containerHBoxGenerate(this, note));
						noteScrollPane.vvalueProperty().bind(extendedFormForScrollingData.heightProperty());
						icerikTA.clear();
	            		} catch(Exception e) {
	            			e.printStackTrace();
	            		}
				} else {
					Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "Not eklenemedi", "Bir sorun oluştu.");
				}
			} else {
				Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "", "Not ile alakalı bir işemri bulunamadı.");
			}
		} else {
			Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı.", "Boş alanlar mevcut", "Lütfen gerekli alanları doldurun!");
		}
		
	}
	
	@Override
	public void doUpdate(AncestorEntity entity, OptionalParamter optionalParameter) {
		Note note = (Note) entity;
		String icerik = optionalParameter.<String>getParamter1();
		note.setIcerik(icerik);
		note = noteRepository.save(note);
		if (note == null) {
			Notifier.passMessage(Alert.AlertType.ERROR, 
					"Uyarı!", "", 
						"Not güncelleme problemi");
		}
		
	}

	public void doRemove(AncestorEntity entity) {
		Note note = (Note) entity;
		Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION, 
													"Uyarı!", "", 
														" Not'u silmek istediğinize emin misiniz?");
		if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
	        noteRepository.removeById(note.getId());
	        try {
		        for(Object obj: extendedFormForScrollingData.getChildren().toArray()) {
		        		Node nd = (Node)obj;
		        		if (String.valueOf(note.getId()).equals(nd.getId())) {
		        			extendedFormForScrollingData.getChildren().remove(nd);
		        			noteScrollPane.vvalueProperty().unbind();
		        		}
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}

}
