package dev.demo.Controller;

import dev.demo.Main;
import dev.demo.Dao.IsemriRepository;
import dev.demo.Dao.IslemRepository;
import dev.demo.Entity.AncestorEntity;
import dev.demo.Entity.Isemri;
import dev.demo.Entity.Islem;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IslemController extends CrudController {

	/* List Page */
	@FXML
	public VBox extendedFormForScrollingData;
    /* Add Page */
	@FXML
	public ComboBox<ColorCategory> kategoriCB;
	@FXML
	public TextArea icerikTA;
    
    @Autowired
	IsemriRepository isEmriRepository;

    @Autowired
    IslemRepository islemRepository;

    @Override
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameter) throws Exception {
    		Isemri isemri = (Isemri) entity;
    		List<Islem> islemList = null;
    		String searchString = null;
    		int isItOk = 0;
    		ColorCategory category = null;
		if (optionalParameter != null) {
			isItOk = optionalParameter.<Integer>getParamter1();
			category = optionalParameter.<ColorCategory>getParamter2();
			searchString = optionalParameter.<String>getParamter3();
		}
		islemList = getCorrectList(isemri, isItOk, category, searchString);
		listPageByParameter(isemri, islemList, isItOk);
		
		if (isItOk == 0) {
			EventGenerator.generateIslemEvents(this, isemri, category, searchString);
		} else {
			EventGenerator.generateIslemEventsTamamPage(this, isemri, category, searchString);
		}
	}
	
	private List<Islem> getCorrectList(Isemri isemri, int isItOk, ColorCategory category, String searchString) {
		List<Islem> islemList = islemRepository.findByIsemriAndDurumOrderByYayinTarihAsc(isemri, isItOk);
		if (category != null && searchString != null) {
			// kategori icinde arama
			islemList = islemRepository.findByIsemriAndDurumAndKategoriAndIcerikContainingOrderByYayinTarihAsc(isemri, isItOk, category.getId(), searchString);
		} else if(category != null && searchString == null) {
			// sadece kategori
			islemList = islemRepository.findByIsemriAndDurumAndKategoriOrderByYayinTarihAsc(isemri, isItOk, category.getId());
		} else if(category == null && searchString != null) {
			// sadece arama
			islemList = islemRepository.findByIsemriAndDurumAndIcerikContainingOrderByYayinTarihAsc(isemri, isItOk, searchString);
		}
		return islemList;
	}

	public void listPageByParameter(Isemri isemri, List<Islem> islemList, int isItOk) throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource(isItOk ==  0 ? "/islem_list.fxml" : "/islem_list_tamam.fxml"));
		ScrollPane islemListView = contentLoader.load();
		main.mainLayout.setCenter(islemListView);
		title.setText("İşlemler\n" + isemri.getMukellef().getUnvan() + "\n " + isemri.getIsemriNo());
		if (!islemList.isEmpty()) {
			ObservableList<ContainerBox> islems = ViewDataGenerator.islemDataGenerate(this, islemList);
			extendedFormForScrollingData.getChildren().addAll(islems);
		}
	}

	@Override
	public void doRemove(AncestorEntity entity) {
		Islem islem = (Islem) entity;
		Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION, 
													"Uyarı!", "", 
														" İşlemi silmek istediğinize emin misiniz?");
		if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
	        islemRepository.removeById(islem.getId());
	        for(Object obj: extendedFormForScrollingData.getChildren().toArray()) {
	        		Node nd = (Node)obj;
	        		if (String.valueOf(islem.getId()).equals(nd.getId())) {
	        			extendedFormForScrollingData.getChildren().remove(nd);
	        			noteScrollPane.vvalueProperty().unbind();
	        		}
	        }    
		}
		
	}

	@Override
	public void doAdd(AncestorEntity entity) {
		Isemri isemri = (Isemri) entity;
		postingChecker.setAddingFlag(true);
		boolean textFieldCheck = postingChecker.emptyCheck(icerikTA);
		boolean comboCheck = postingChecker.emptyCheck(kategoriCB);
		if ((textFieldCheck && comboCheck)|| postingChecker.isSpecialConditionFlag()) {
			postingChecker.setSpecialConditionFlag(false);
			isemri = isEmriRepository.findOne(isemri.getId());
			if (isemri != null) {
				Islem islem = new Islem();
				islem.setIsemri(isemri);
				ObjectInitializer.islemInitialize(this, islem);
				islem = islemRepository.save(islem);
				if (islem != null) {
					try {
						extendedFormForScrollingData.getChildren().add(ViewDataGenerator.containerHBoxGenerate(this, islem));
						noteScrollPane.vvalueProperty().bind(extendedFormForScrollingData.heightProperty());
						icerikTA.clear();
	            		} catch(Exception e) {
	            			e.printStackTrace();
	            		}
				} else {
					Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "İşlem eklenemedi", "Bir sorun oluştu.");
				}
			} else {
				Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "", "İşlem ile alakalı bir işemri bulunamadı.");
			}
		} else {
			Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı.", "Boş alanlar mevcut", "Lütfen gerekli alanları doldurun!");
		}
		
	}
	
	@Override
	public void doUpdate(AncestorEntity entity, OptionalParamter optionalParameter) {
		Islem islem = (Islem) entity;
		if (optionalParameter == null) {
			doOkUpdate(islem);
		} else {
			String icerik = optionalParameter.<String>getParamter1();
			islem.setIcerik(icerik);
			islem = islemRepository.save(islem);
			if (islem == null) {
				Notifier.passMessage(Alert.AlertType.ERROR, 
						"Uyarı!", "", 
							"İşlem güncelleme problemi.");
			}
		}
	}
	
	public void doOkUpdate(Islem islem) {
		int durum = 0;
		Date tamamTarih = null;
		if (islem.getDurum() == 0) {
			durum = 1;
			tamamTarih = Date.valueOf(LocalDate.now());
		} 
		islem.setTamamTarih(tamamTarih);
		islem.setDurum(durum);
		
		islem = islemRepository.save(islem);
		if (islem != null) {
			for(Object obj: extendedFormForScrollingData.getChildren().toArray()) {
	        		Node nd = (Node)obj;
	        		if (String.valueOf(islem.getId()).equals(nd.getId())) {
	        			extendedFormForScrollingData.getChildren().remove(nd);
	        			noteScrollPane.vvalueProperty().unbind();
	        		}
			}
		} else {	
			Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "İşlem eklenemedi.", "Bir sorun oluştu.");
		}
	}

}
