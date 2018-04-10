package dev.demo.Controller;

import dev.demo.Dao.IsemriRepository;
import dev.demo.Dao.IslemRepository;
import dev.demo.Dao.MukellefRepository;
import dev.demo.Dao.MulakatRepository;
import dev.demo.Dao.NoteRepository;
import dev.demo.Dao.SmmRepository;
import dev.demo.Entity.AncestorEntity;
import dev.demo.Entity.Isemri;
import dev.demo.Entity.Islem;
import dev.demo.Entity.Mukellef;
import dev.demo.Entity.Mulakat;
import dev.demo.Entity.Note;
import dev.demo.Main;
import dev.demo.Services.*;
import dev.demo.Services.SpecialEnums.TextEnum;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class IsemriController extends CrudController {

	@FXML
	private VBox form;

	/* updateIsemri Page */
	@FXML
    public Button kaydetButton;

	/* Show Page */
	@FXML
	private VBox mukellefBilgiContent;
	@FXML
	private VBox isemriBilgiContent;
	@FXML
	public Button mukellefShowButton;
	@FXML
	public Button isemriGuncelleButton;
	@FXML
	public Button silButton;
	@FXML
    public Button raporAlButton;
	@FXML
	public Button notlarListButton;
	@FXML
	public Button islemlerListButton;
	@FXML
	public Button mulakatListButton;

	/* isEmriMain Page */
	@FXML
	public TableColumn<Isemri, String> isemriNoTC;
	@FXML
	public TableColumn<Isemri, String> mukellefAdSoyadTC;
	@FXML
	public TableColumn<Isemri, String> mukellefVergiDaireTC;
	@FXML
	public TableColumn<Isemri, String> tarhVergiDaireTC;
	@FXML
	public TableColumn<Isemri, String> incelemeTurTC;
	@FXML
	public TableColumn<Isemri, String> konuTC;
	@FXML
	public TableColumn<Isemri, String> incelemeYilTC;
	@FXML
	public TableColumn<Isemri, String> isemriYayinTarihTC;
	@FXML
	public TableColumn<Isemri, String> isemriTamamTarihTC;
	@FXML
	public TableColumn<Isemri, String> durumTC;
	@FXML
	public TableColumn<Isemri, String> kalanGunTC;
	
	/* isEmriEkle Page */
	@FXML
	public TextField isEmriNoTF;
	@FXML
	public DatePicker isEmriDefaultTarihDP;
	@FXML
	public TextField isEmriKonuTA;
	@FXML
	public TextField tarhVergiDaireTF;
	@FXML
	public DatePicker isEmriTarihDP;
	@FXML
	public ComboBox<Mukellef> vergiKimlikNoCB;
	@FXML
	public TextField incelemeYilTF;
	@FXML
	public ComboBox<TextEnum> incelemeTurCB;
	@FXML
	public ComboBox<TextEnum> durumCB;
	
	/* general */
    @FXML
    public Button backButton;

	@Autowired
	MukellefRepository mukellefRepository;

	@Autowired
	SmmRepository smmRepository;

	@Autowired
	IsemriRepository isEmriRepository;
	
	@Autowired
	IslemRepository islemRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	MulakatRepository mulakatRepository;
	
	@Override
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameter) throws Exception {
		Mukellef mukellef = (Mukellef) entity;
		String searchString = optionalParameter == null ? null : optionalParameter.<String>getParamter3();
		getCorrectPage(mukellef, searchString);
		listPageByParameter();	
		if (mukellef != null) {
			backButton.setVisible(true);
			title.setText("Mukellef " + mukellef.getUnvan() + " ile alakalı İş Emirleri");
		}
		EventGenerator.generateListPageEvents(this, searchString, mukellef, "isemriYayinTarih", "id");
	}
	
	public void listPageByParameter() throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource("/is_emri_list.fxml"));
		ScrollPane isemriListView = contentLoader.load();
		main.mainLayout.setCenter(isemriListView);
		@SuppressWarnings("unchecked")
		List<Isemri> isemriList = (List<Isemri>) page.getContent();
		listTable.getItems().clear();
		for (Isemri isemri : isemriList) {
			listTable.getItems().add(isemri);
			ViewDataGenerator.isemriListDataGenerate(this);
		}
	}
	
	private void getCorrectPage(Mukellef mukellef, String searchString) {
		if (mukellef == null) {
			if (searchString == null) {
				page =  isEmriRepository.findAllByOrderByIsemriYayinTarihDescIdDesc(getPageRequest());
			} else {
				page =  isEmriRepository.findAllByIsemriNoContainingOrderByIsemriYayinTarihDescIdDesc(searchString, getPageRequest());
			}
		} else {
			if (searchString == null) {
				page =  isEmriRepository.findAllByMukellefOrderByIsemriYayinTarihDescIdDesc(mukellef, getPageRequest());
			} else {
				page =  isEmriRepository.findAllByMukellefAndIsemriNoContainingOrderByIsemriYayinTarihDescIdDesc(mukellef, searchString, getPageRequest());
			}
		}
	}

	@Override
	public void addPage(AncestorEntity entity) throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource("/is_emri_ekle.fxml"));
		ScrollPane isEmriEkleView = contentLoader.load();
		main.mainLayout.setCenter(isEmriEkleView);
		List<Mukellef> mukellefList = mukellefRepository.findAllByOrderByUnvanAsc();
		if (mukellefList.size() < 1) {
			Notifier.passMessage(
					Alert.AlertType.WARNING, 
					"Uyarı.", "", 
					"Mükellef Bulunmuyor.");
		}
		
		ViewDataGenerator.isemriAddAndUpdateComboboxDataGenerate(this, mukellefList);
		EventGenerator.generateAddPageEvents(this, null);
	}

	@Override
	public void doAdd(AncestorEntity entity) {
		postingChecker.setAddingFlag(true);
		boolean textFieldCheck = postingChecker.emptyCheck(isEmriNoTF, isEmriKonuTA, tarhVergiDaireTF, incelemeYilTF);
		boolean comboCheck = postingChecker.emptyCheck(incelemeTurCB, vergiKimlikNoCB);
		if (vergiKimlikNoCB.getValue() != null) {
		if ((textFieldCheck && comboCheck) || postingChecker.isSpecialConditionFlag()) {
			postingChecker.setSpecialConditionFlag(false);
				Mukellef mukellef = mukellefRepository.findOne(Integer.parseInt(vergiKimlikNoCB.getId()));
				Isemri isemri = new Isemri();
				ObjectInitializer.isemriInitialize(this, isemri, false);
				isemri.setMukellef(mukellef);
				isemri = isEmriRepository.save(isemri);
				if (isemri != null) {
					Notifier.passMessage(Alert.AlertType.INFORMATION, "Bilgilendirme.", "", "İş Emri eklendi.");
					try {
        					showPage(isemri);
	            		} catch(Exception e) {
	            			e.printStackTrace();
	            		}
				} else {
					Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "İş Emri eklenemedi.", "Bir sorun oluştu.");
				}
		} else {
			Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
					"Uyarı.",
					"",
					"Boş alanlar mevcut. Devam etmek ister misiniz?");
			if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
				postingChecker.setSpecialConditionFlag(true);
				doAdd(null);
			}
		}
		} else {
			Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "", "Mükellef seçmelisiniz!");
		}
	}

	@Override
	public void showPage(AncestorEntity entity)  throws Exception {
		setLoader();
		Isemri isemri = (Isemri) entity;
		contentLoader.setLocation(Main.class.getResource("/isemri_show.fxml"));
		ScrollPane isemriShowView = contentLoader.load();
		main.mainLayout.setCenter(isemriShowView);

		Mukellef mukellef = isemri.getMukellef();
		ObservableList<MyHBox> mukellefData = ViewDataGenerator.mukellefShowDataGenerate(mukellef);
		mukellefBilgiContent.getChildren().addAll(mukellefData);

		ObservableList<MyHBox> isemriData = ViewDataGenerator.isemriShowDataGenerate(isemri);
		isemriBilgiContent.getChildren().addAll(isemriData);
		EventGenerator.generateIsemriShowPageEvents(this, isemri);
	}

	public void doRemove(AncestorEntity entity) {
		Isemri isemri = (Isemri) entity;
		Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION, "Uyarı!", "",
				"İş emri ni silmek istediğinize emin misiniz?");
		if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			isEmriRepository.removeById(isemri.getId());
			try {
				listPage(null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updatePage(AncestorEntity entity) throws Exception {
		setLoader();
		Isemri isemri = (Isemri) entity;
        contentLoader.setLocation(Main.class.getResource("/update_is_emri.fxml"));
        ScrollPane updateIsemriView = contentLoader.load();
        main.mainLayout.setCenter(updateIsemriView);
        List<Mukellef> mukellefList = mukellefRepository.findAllByOrderByUnvanAsc();
        ViewDataGenerator.isemriAddAndUpdateComboboxDataGenerate(this, mukellefList);
        ViewDataGenerator.isemriUpdateFormDataGenerate(this, isemri);
        EventGenerator.generateUpdatePageEvents(this, isemri);
    }

	@Override
	public void doUpdate(AncestorEntity entity, OptionalParamter optionalPrarameter) {
		Isemri onHandIsemri = (Isemri) entity;
		postingChecker.setAddingFlag(true);
		boolean textFieldCheck = postingChecker.emptyCheck(isEmriNoTF, isEmriKonuTA, tarhVergiDaireTF, incelemeYilTF);
		boolean comboCheck = postingChecker.emptyCheck(incelemeTurCB, vergiKimlikNoCB);
		if ((textFieldCheck && comboCheck) || postingChecker.isSpecialConditionFlag()) {
			postingChecker.setSpecialConditionFlag(false);
			onHandIsemri = isEmriRepository.findOne(onHandIsemri.getId());
			Mukellef mukellef = mukellefRepository.findOne(Integer.parseInt(vergiKimlikNoCB.getId()));
			if (onHandIsemri != null) {
				if (mukellef != null) {
					ObjectInitializer.isemriInitialize(this, onHandIsemri, true);
					onHandIsemri.setMukellef(mukellef);
					onHandIsemri = isEmriRepository.save(onHandIsemri);
					if (onHandIsemri != null) {
						Notifier.passMessage(Alert.AlertType.INFORMATION, "Bilgilendirme.", "", "İş Emri güncellendi.");
						try {
							Follower.stepReduce();
                				showPage(onHandIsemri);
	                		} catch(Exception e) {
	                			e.printStackTrace();
	                		}
					} else {
						Notifier.passMessage(Alert.AlertType.ERROR, "Hata.", "İş Emri güncellenemedi.", "Bir sorun oluştu.");
					}
				} else {
					Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı!", "", "Geçerli bir mükellef bulunamadı.");
				} 
			} else {
				Notifier.passMessage(Alert.AlertType.WARNING, "Uyarı!", "", "İşemri bulunamadı.");
			} 
		} else {
			Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
					"Uyarı.",
					"",
					"Boş alanlar mevcut. Devam etmek ister misiniz?");
			if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
				postingChecker.setSpecialConditionFlag(true);
				doUpdate(onHandIsemri,null);
			}
		}
	}

	public void printRapor(Isemri isemri) throws Exception {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(main.getPrimaryStage());

		if (selectedDirectory != null) {
			String path = selectedDirectory.getAbsolutePath();
			path = path + "/" + isemri.getIsemriNo();

			RaporPrinter raporPrinter = new RaporPrinter(path);
			raporPrinter.open();

			raporPrinter.printFrontCover(isemri);
			raporPrinter.newPage();

			raporPrinter.printMukellefIsemri(isemri);
			raporPrinter.newPage();

			List<Islem> islemList = islemRepository.findByIsemriAndDurumOrderByYayinTarihAsc(isemri, 1);
			raporPrinter.printIslem(islemList);
			raporPrinter.newPage();

			List<Note> noteList = noteRepository.findByIsemriOrderByYayinTarihAsc(isemri);
			raporPrinter.printNote(noteList);
			raporPrinter.newPage();

			List<Mulakat> mulakatList = mulakatRepository.findByIsemriOrderByYayinTarihAsc(isemri);
			raporPrinter.printMulakat(mulakatList);
			raporPrinter.close();

			raporPrinter.addPageNumberToPdf();

			Notifier.passMessage(Alert.AlertType.INFORMATION, "Bilgi!", "", "Rapor yazdırıldı.");
		}
	}
}
