package dev.demo.Controller;

import dev.demo.Main;
import dev.demo.Dao.IsemriRepository;
import dev.demo.Dao.MulakatRepository;
import dev.demo.Entity.AncestorEntity;
import dev.demo.Entity.Isemri;
import dev.demo.Entity.Mulakat;
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
public class MulakatController extends CrudController {
	
	/* List Page */
	@FXML
	public VBox extendedFormForScrollingData;
    /* Add Page */
    @FXML
	public ComboBox<ColorCategory> kategoriCB;
	@FXML
	public TextArea icerikTA;
	
    @Autowired
    MulakatRepository mulakatRepository;
    
    @Autowired
	IsemriRepository isEmriRepository;
    
    @Override
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameter) throws Exception {
		Isemri isemri = (Isemri) entity;
		List<Mulakat> mulakatList = null;
		String searchString = null;
		ColorCategory category = null;
		int isItOk = 0;
		if (optionalParameter == null) {
			isItOk = 3;
		} else {
			isItOk = optionalParameter.<Integer>getParamter1();
			category = optionalParameter.<ColorCategory>getParamter2();
			searchString = optionalParameter.<String>getParamter3();
		}
		mulakatList = getCorrectList(isemri, isItOk, category, searchString);
		listPageByParameter(isemri, mulakatList);	
		EventGenerator.generateMulakatEvents(this, isemri, isItOk, category, searchString);
	}
	
	public void listPageByParameter(Isemri isemri, List<Mulakat> mulakatList) throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource("/mulakat_list.fxml"));
		ScrollPane mualaktListView = contentLoader.load();
		main.mainLayout.setCenter(mualaktListView);
		title.setText("Mülakatlar\n" + isemri.getMukellef().getUnvan() + "\n " + isemri.getIsemriNo());
		if (!mulakatList.isEmpty()) {
			ObservableList<ContainerBox> mulakats = ViewDataGenerator.mulakatDataGenerate(this, mulakatList);
			extendedFormForScrollingData.getChildren().addAll(mulakats);
		}
	}
	
	private List<Mulakat> getCorrectList(Isemri isemri, int isItOK, ColorCategory category, String searchString) {
		List<Mulakat> mulakatList = mulakatRepository.findByIsemriOrderByYayinTarihAsc(isemri);
		if (isItOK == 3) {
			if (category != null && searchString != null) {
				// kategori icinde arama
				mulakatList = mulakatRepository.findByIsemriAndKategoriAndIcerikContainingOrderByYayinTarihAsc(isemri,category.getId(), searchString);
			} else if(category != null && searchString == null) {
				// sadece kategori
				mulakatList = mulakatRepository.findByIsemriAndKategoriOrderByYayinTarihAsc(isemri,category.getId());
			} else if(category == null && searchString != null) {
				// sadece arama
				mulakatList = mulakatRepository.findByIsemriAndIcerikContainingOrderByYayinTarihAsc(isemri, searchString);
			}
		} else {
			if (category != null && searchString != null) {
				// kategori icinde arama
				mulakatList = mulakatRepository.findByIsemriAndDurumAndKategoriAndIcerikContainingOrderByYayinTarihAsc(isemri, isItOK, category.getId(), searchString);
			} else if(category != null && searchString == null) {
				// sadece kategori
				mulakatList = mulakatRepository.findByIsemriAndDurumAndKategoriOrderByYayinTarihAsc(isemri, isItOK, category.getId());
			} else if(category == null && searchString != null) {
				// sadece arama
				mulakatList = mulakatRepository.findByIsemriAndDurumAndIcerikContainingOrderByYayinTarihAsc(isemri, isItOK, searchString);
			} else {
				mulakatList = mulakatRepository.findByIsemriAndDurumOrderByYayinTarihAsc(isemri, isItOK);
			}
		}
		return mulakatList;
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
				Mulakat mulakat = new Mulakat();
				mulakat.setIsemri(isemri);
				ObjectInitializer.mulakatInitialize(this, mulakat, false);
				mulakat = mulakatRepository.save(mulakat);
				if (mulakat != null) {
					try {
						extendedFormForScrollingData.getChildren().add(ViewDataGenerator.containerHBoxGenerate(this, mulakat));
						noteScrollPane.vvalueProperty().bind(extendedFormForScrollingData.heightProperty());
						icerikTA.clear();
	            		} catch(Exception e) {
	            			e.printStackTrace();
	            		}
				} else {
					Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "Mülakat eklenemedi", "Bir sorun oluştu.");
				}
			} else {
				Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "", "Mülakat ile alakalı bir işemri bulunamadı.");
			}
		} else {
			Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı.", "Boş alanlar mevcut", "Lütfen gerekli alanları doldurun!");
		}
		
	}
	
	public void doUpdate(AncestorEntity entity, OptionalParamter optionalParameter) {
		Mulakat mulakat = (Mulakat) entity;

		if (optionalParameter == null) {
			doOkUpdate(mulakat);
		} else {
			String icerik = optionalParameter.<String>getParamter1();
			mulakat.setYayinTarih(Date.valueOf(LocalDate.now()));
			mulakat.setIcerik(icerik);
			mulakat = mulakatRepository.save(mulakat);
			if (mulakat == null) {
				Notifier.passMessage(Alert.AlertType.ERROR,
						"Uyarı!", "",
						"Mülakat güncelleme problemi.");
			}
		}
	}

	public void doOkUpdate(Mulakat mulakat) {
		int durum = 0;
		Date tamamTarih = null;
		if (mulakat.getDurum() == 0) {
			durum = 1;
			tamamTarih = Date.valueOf(LocalDate.now());
		}
		mulakat.setTamamTarih(tamamTarih);
		mulakat.setDurum(durum);
		mulakat = mulakatRepository.save(mulakat);
		if (mulakat == null ) {
			Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "Mülakat güncellenemedi.", "Bir sorun oluştu.");
		}
	}

	public void doRemove(AncestorEntity entity) {
		Mulakat mulakat = (Mulakat) entity;
		Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION, 
													"Uyarı!", "", 
														"Mülakat'ı silmek istediğinize emin misiniz?");
		if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
	        mulakatRepository.removeById(mulakat.getId());
	        try {
		        for(Object obj: extendedFormForScrollingData.getChildren().toArray()) {
		        		Node nd = (Node)obj;
		        		if (String.valueOf(mulakat.getId()).equals(nd.getId())) {
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
