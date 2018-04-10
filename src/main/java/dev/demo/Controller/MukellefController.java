package dev.demo.Controller;

import dev.demo.Dao.MukellefRepository;
import dev.demo.Dao.SmmRepository;
import dev.demo.Dao.TemsilciRepository;
import dev.demo.Dao.YmmRepository;
import dev.demo.Entity.*;
import dev.demo.Main;
import dev.demo.Services.EventGenerator;
import dev.demo.Services.Follower;
import dev.demo.Services.MyHBox;
import dev.demo.Services.Notifier;
import dev.demo.Services.ObjectInitializer;
import dev.demo.Services.OptionalParamter;
import dev.demo.Services.ViewDataGenerator;
import dev.demo.Services.SpecialEnums.TextEnum;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class MukellefController extends CrudController {
	
    /*  Mukellef List Page */
    @FXML
    public TableColumn<Mukellef, String> mukellefTitleTC;
    @FXML
    public TableColumn<Mukellef, String> vergiKimlikNoTC;
    @FXML
    public TableColumn<Mukellef, String> vergiDaireTC;
    @FXML
    public TableColumn<Mukellef, String> telefonNoTC;
    @FXML
    public TableColumn<Mukellef, String> emailTC;
    @FXML
    public TableColumn<Mukellef, String> isYeriAdresTC;
    @FXML
    public TableColumn<Mukellef, String> ikametgahAdresTC;
    @FXML
    public TableColumn<Mukellef, String> eklenmeTarihTC;
    @FXML
    public TableView<Mukellef> listTable;
    
    /*  Mukellef Adding Form */
    @FXML
    public TextField vergiKimlikNoTF;
    @FXML
    public TextField tcKimlikNoTF;
    @FXML
    public TextField unvanTF;
    @FXML
    public ComboBox<String> vergiDaireCB;
    @FXML
    public ComboBox<TextEnum> faaliyetDurumCB;
    @FXML
    public TextField faaliyetKonuTF;
    @FXML
    public VBox terkTarihInput;
    @FXML
    public Label terkTarihLabel = new Label("Terk Tarihi:");
    @FXML
    public DatePicker terkTarihDP = new DatePicker();
    @FXML
    public TextField telefonTF;
    @FXML
    public TextField gsmTelefonTF;
    @FXML
    public TextField emailTF;
    @FXML
    public TextField isYeriAdresTA;
    @FXML
    public TextField ikametgahAdresTA;
    @FXML
    public TextField temsilciAdSoyadTF;
    @FXML
    public TextField temsilciTcKimlikNoTF;
    @FXML
    public TextField temsilciTelefonTF;
    @FXML
    public TextField temsilciEmailTF;
    @FXML
    public TextField temsilciIkametgahAdresTA;
    @FXML
    public TextField smmAdSoyadTF;
    @FXML
    public TextField smmTelefonTF;
    @FXML
    public TextField smmEmailTF;
    @FXML
    public TextField ymmAdSoyadTF;
    @FXML
    public TextField ymmTelefonTF;
    @FXML
    public TextField ymmEmailTF;
    
    /* updateMukellef Page*/
    
    /* mukellefShow Page*/
    @FXML
    private VBox mukellefBilgiContent;
    @FXML
    private VBox temsilciBilgiContent;
    @FXML
    private VBox smmBilgiContent;
    @FXML
    private VBox ymmBilgiContent;
    @FXML
    public Button temsilciGuncelleButton;
    @FXML
    public Button smmGuncelleButton;
    @FXML
    public Button ymmGuncelleButton;
    @FXML
    public Button listIsemriButton;
    
    /* general */
    @Autowired
    MukellefRepository mukellefRepository;

    @Autowired
    TemsilciRepository temsilciRepository;

    @Autowired
    SmmRepository smmRepository;
    
    @Autowired
    YmmRepository ymmRepository;
    
    @Override
	public void listPage(AncestorEntity entity, OptionalParamter optionalParameter) throws Exception {
    		String searchString = optionalParameter == null ? null : optionalParameter.<String>getParamter3();
		if (searchString != null) {
			page =  mukellefRepository.findAllByUnvanContainingOrderByKayitTarihDescMukellefIdDesc(searchString, getPageRequest());
		} else {
			page =  mukellefRepository.findAllByOrderByKayitTarihDescMukellefIdDesc(getPageRequest());
		}
		listPageByParameter();	
		EventGenerator.generateListPageEvents(this, searchString, entity, "kayitTarih", "mukellefId");
	}
	
	public void listPageByParameter() throws Exception {
		setLoader();
		contentLoader.setLocation(Main.class.getResource("/mukellef_list.fxml"));
        ScrollPane mukellefListView = contentLoader.load();
        main.mainLayout.setCenter(mukellefListView);
		@SuppressWarnings("unchecked")
		List<Mukellef> mukellefList =  (List<Mukellef>) page.getContent();
		listTable.getItems().clear();
        for (Mukellef mukellef: mukellefList) {
            listTable.getItems().add(mukellef);
            ViewDataGenerator.mukellefListDataGenerate(this);
        }
	}

    @Override
    public void addPage(AncestorEntity entity) throws Exception {
        setLoader();
		contentLoader.setLocation(Main.class.getResource("/new_mukellef.fxml"));
 	   	ScrollPane newMukellefView = contentLoader.load();
        main.mainLayout.setCenter(newMukellefView);
        EventGenerator.generateAddPageEvents(this, null);
        EventGenerator.generateCheckBoxEvents(this);
    }

    @Override
    public void doAdd(AncestorEntity entity) {
        postingChecker.setAddingFlag(true);
        boolean textFieldCheck = postingChecker.emptyCheck(unvanTF,vergiKimlikNoTF,tcKimlikNoTF,faaliyetKonuTF,
                telefonTF,gsmTelefonTF,emailTF,isYeriAdresTA,ikametgahAdresTA,
                temsilciAdSoyadTF, temsilciTcKimlikNoTF, temsilciTelefonTF,temsilciEmailTF, temsilciIkametgahAdresTA,
                smmAdSoyadTF,smmTelefonTF,smmEmailTF,
                ymmAdSoyadTF,ymmTelefonTF,ymmEmailTF);
        boolean comboCheck = postingChecker.emptyCheck(vergiDaireCB,faaliyetDurumCB);
        if (!("".equals(vergiKimlikNoTF.getText().trim()))) {
        if ((textFieldCheck && comboCheck) || postingChecker.isSpecialConditionFlag()) {
            postingChecker.setSpecialConditionFlag(false);
            Mukellef findMukellef = mukellefRepository.findByMukellefVergiKimlikNo(vergiKimlikNoTF.getText().trim());
            if (findMukellef != null) {
            		Notifier.passMessage(Alert.AlertType.ERROR,
            					"Hata.",
            					"",
            					"Bu vergi kimlik no yu kullanan başka bir mükellef sisteme kayıtlı.");
            } else {
                    Mukellef mukellef = new Mukellef();
                    ObjectInitializer.mukellefInitialize(this, mukellef, false);
                    mukellef = mukellefRepository.save(mukellef);
                    if (mukellef != null) {
                        Temsilci temsilci = new Temsilci();
                        ObjectInitializer.temsilciInitialize(this, temsilci);
                        temsilci.setMukellef(mukellef);
                        temsilci = temsilciRepository.save(temsilci);

                        Smm smm = new Smm();
                        ObjectInitializer.smmInitialize(this, smm);
                        smm.setMukellef(mukellef);
                        smm = smmRepository.save(smm);
                        
                        Ymm ymm = new Ymm();
                        ObjectInitializer.ymmInitialize(this, ymm);
                        ymm.setMukellef(mukellef);
                        ymm = ymmRepository.save(ymm);
                        
                        if (smm != null && ymm != null && temsilci != null) {
                        		Notifier.passMessage(Alert.AlertType.INFORMATION,
                        					"Bilgilendirme.",
                        					"",
                        					"Mükellef eklendi.");
                        		try {
                        			listPage(null, null);
                        		} catch(Exception e) {
                        			e.printStackTrace();
                        		}
                        } else {
                        		Notifier.passMessage(Alert.AlertType.ERROR,
                        					"Hata.",
                        					"",
                        					"Mükellef eklendi fakat, Smm veya Ymm eklenemedi.");
                        }
                    } else {
                    		Notifier.passMessage(Alert.AlertType.ERROR,
                    					"Hata.",
                    					"",
                    					"Mükellef eklenemedi.");
                    }

            }
        } else {
            Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
                    "Uyarı.",
                    "",
                    "Boş alanlar mevcut. Devam etmek ister misiniz?");
            if (result.get() == ButtonType.OK) {
                postingChecker.setSpecialConditionFlag(true);
                doAdd(null);
            }
        }
        } else {
            Notifier.passMessage(Alert.AlertType.ERROR,
                    "Hata.",
                    "",
                    "Vergi Kimlik No Alanı boş geçilemez.");
        }
    }
    
    @Override
    public void showPage(AncestorEntity entity) throws Exception {
        setLoader();
		Mukellef mukellef = (Mukellef) entity;
        contentLoader.setLocation(Main.class.getResource("/mukellef_show.fxml"));
        ScrollPane mukellefShowView = contentLoader.load();
        main.mainLayout.setCenter(mukellefShowView);
        title.setText("Mükellef - " + mukellef.getUnvan());
        ObservableList<MyHBox> mukellefData = ViewDataGenerator.mukellefShowDataGenerate(mukellef);
        mukellefBilgiContent.getChildren().addAll(mukellefData);

        Temsilci temsilci = mukellef.getTemsilci();
        ObservableList<MyHBox> temsilciData = ViewDataGenerator.temsilciShowDataGenerate(temsilci);
        temsilciBilgiContent.getChildren().addAll(temsilciData);

        Smm smm = mukellef.getSmm();
        ObservableList<MyHBox> smmData = ViewDataGenerator.smmShowDataGenerate(smm);
        smmBilgiContent.getChildren().addAll(smmData);
        
        Ymm ymm = mukellef.getYmm();
        ObservableList<MyHBox> ymmData = ViewDataGenerator.ymmShowDataGenerate(ymm);
        ymmBilgiContent.getChildren().addAll(ymmData);
        
        EventGenerator.generateMukellefShowPageEvents(this, mukellef);
    }
 
    @Override
    public void doRemove(AncestorEntity entity) {
    		Mukellef mukellef = (Mukellef) entity;
    		Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION, 
    													"Uyarı!", "", 
    													"Mükellef ile alakalı İş emirleri silinecek, Mükellef'i silmek istediğinize emin misiniz?");
		if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
	        mukellefRepository.removeByMukellefId(mukellef.getMukellefId());
	        try {
	            listPage(null, null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
    }
    
    @Override
    public void updatePage(AncestorEntity entity) throws Exception {
    		if (entity instanceof Temsilci) {
    			Temsilci temsilci = (Temsilci) entity;
    			uPage(temsilci);
    		} else if (entity instanceof Smm) {
                Smm smm = (Smm) entity;
    			uPage(smm);
    		} else if (entity instanceof Ymm) {
    			Ymm ymm = (Ymm) entity;
    			uPage(ymm);
    		} else {
	    		Mukellef mukellef = (Mukellef) entity;
	    		setLoader();
                contentLoader.setLocation(Main.class.getResource("/update_mukellef.fxml"));
                ScrollPane updateMukellefView = contentLoader.load();
                main.mainLayout.setCenter(updateMukellefView);
                ViewDataGenerator.mukellefUpdateFormDataGenerate(this, mukellef);
                EventGenerator.generateUpdatePageEvents(this, mukellef);
                EventGenerator.generateCheckBoxEvents(this);
    		}
    }
    
    public void uPage(Temsilci temsilci) throws Exception {
        setLoader();
        contentLoader.setLocation(Main.class.getResource("/update_temsilci.fxml"));
        ScrollPane updateSmmView = contentLoader.load();
        main.mainLayout.setCenter(updateSmmView);
        ViewDataGenerator.temsilciUpdateFormDataGenerate(this, temsilci);
        EventGenerator.generateTemsilciUpdatePageEvents(this, temsilci);
    }

    public void uPage(Smm smm) throws Exception {
        setLoader();
        contentLoader.setLocation(Main.class.getResource("/update_smm.fxml"));
        ScrollPane updateSmmView = contentLoader.load();
        main.mainLayout.setCenter(updateSmmView);
        ViewDataGenerator.smmUpdateFormDataGenerate(this, smm);
        EventGenerator.generateSmmUpdatePageEvents(this, smm);
    }
    
    public void uPage(Ymm ymm) throws Exception {
	    	setLoader();
	    	contentLoader.setLocation(Main.class.getResource("/update_ymm.fxml"));
	    	ScrollPane updateYmmView = contentLoader.load();
	    	main.mainLayout.setCenter(updateYmmView);
	    	ViewDataGenerator.ymmUpdateFormDataGenerate(this, ymm);
	    	EventGenerator.generateYmmUpdatePageEvents(this, ymm);
    }
    
    @Override
    public void doUpdate(AncestorEntity entity, OptionalParamter optionalPrarameter) {
        Mukellef onHandMukellef = (Mukellef) entity;
        postingChecker.setAddingFlag(true);
        boolean textFieldCheck = postingChecker.emptyCheck(unvanTF, vergiKimlikNoTF,tcKimlikNoTF,faaliyetKonuTF,telefonTF,gsmTelefonTF,emailTF,isYeriAdresTA,ikametgahAdresTA);
        boolean comboCheck = postingChecker.emptyCheck(vergiDaireCB,faaliyetDurumCB);
        if (!("".equals(vergiKimlikNoTF.getText().trim()))) {
        if ((textFieldCheck && comboCheck) || postingChecker.isSpecialConditionFlag()) {
            postingChecker.setSpecialConditionFlag(false);
            onHandMukellef = mukellefRepository.findOne(onHandMukellef.getMukellefId());
            if (onHandMukellef != null) {
                		ObjectInitializer.mukellefInitialize(this, onHandMukellef, true);
                		onHandMukellef = mukellefRepository.save(onHandMukellef);
                    if (onHandMukellef != null) {
                    		Notifier.passMessage(Alert.AlertType.INFORMATION,
                                    "Bilgilendirme.",
                                    "",
                                    "Mükellef güncellendi.");
                    		try {
                    			Follower.stepReduce();
                    			showPage(onHandMukellef);
                    		} catch(Exception e) {
                    			e.printStackTrace();
                    		}
                    } else {
	                    	Notifier.passMessage(Alert.AlertType.ERROR,
	                                "Hata.",
	                                "",
	                                "Mükellef güncellenemedi.");
                    }
            }
        } else {
            Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
                    "Uyarı.",
                    "",
                    "Boş alanlar mevcut. Devam etmek ister misiniz?");
            if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                postingChecker.setSpecialConditionFlag(true);
                doUpdate(onHandMukellef, null);
            }
        }
        } else {
            Notifier.passMessage(Alert.AlertType.ERROR,
                    "Hata.",
                    "",
                    "Vergi Kimlik No Alanı boş geçilemez.");
        }
    }

    public void doUpdate(Temsilci temsilci) {
        postingChecker.setAddingFlag(true);
        boolean textFieldCheck = postingChecker.emptyCheck(temsilciAdSoyadTF, temsilciTcKimlikNoTF, temsilciTelefonTF, temsilciEmailTF, temsilciIkametgahAdresTA);
        if (textFieldCheck || postingChecker.isSpecialConditionFlag()) {
            postingChecker.setSpecialConditionFlag(false);
            ObjectInitializer.temsilciInitialize(this, temsilci);
            temsilci = temsilciRepository.save(temsilci);
            if (temsilci != null) {
                Notifier.passMessage(Alert.AlertType.INFORMATION,
                        "Bilgilendirme.",
                        "",
                        "Temsilci güncellendi.");
                try {
                    Follower.stepReduce();
                    showPage(temsilci.getMukellef());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                Notifier.passMessage(Alert.AlertType.ERROR,
                        "Hata.",
                        "",
                        "Temsilci guncellenemedi.");
            }
        } else {
            Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
                    "Uyarı.",
                    "",
                    "Boş alanlar mevcut. Devam etmek ister misiniz?");
            if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                postingChecker.setSpecialConditionFlag(true);
                doUpdate(temsilci);
            }
        }
    }
    
    public void doUpdate(Smm smm) {
        postingChecker.setAddingFlag(true);
        boolean textFieldCheck = postingChecker.emptyCheck(smmAdSoyadTF, smmTelefonTF, smmEmailTF);
        if (textFieldCheck || postingChecker.isSpecialConditionFlag()) {
	        postingChecker.setSpecialConditionFlag(false);
	    	    	ObjectInitializer.smmInitialize(this, smm);
	    	    	smm = smmRepository.save(smm);
	        if (smm != null) {
	            	Notifier.passMessage(Alert.AlertType.INFORMATION,
	                        "Bilgilendirme.",
	                        "",
	                        "Smm güncellendi.");
	            	try {
	            		Follower.stepReduce();
	        			showPage(smm.getMukellef());
	        		} catch(Exception e) {
	        			e.printStackTrace();
	        		}
	        } else {
	            	Notifier.passMessage(Alert.AlertType.ERROR,
	                        "Hata.",
	                        "",
	                        "Smm guncellenemedi.");
	        }
        } else {
            Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
                    "Uyarı.",
                    "",
                    "Boş alanlar mevcut. Devam etmek ister misiniz?");
            if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                postingChecker.setSpecialConditionFlag(true);
                doUpdate(smm);
            }
        }
    }
    
    public void doUpdate(Ymm ymm) {
        postingChecker.setAddingFlag(true);
        boolean textFieldCheck = postingChecker.emptyCheck(ymmAdSoyadTF, ymmTelefonTF, ymmEmailTF);
        if (textFieldCheck || postingChecker.isSpecialConditionFlag()) {
		    postingChecker.setSpecialConditionFlag(false);
		    	ObjectInitializer.ymmInitialize(this, ymm);
		    	ymm = ymmRepository.save(ymm);
		    	if (ymm != null) {
		    		Notifier.passMessage(Alert.AlertType.INFORMATION,
		    						"Bilgilendirme.",
		    						"",
		    						"Ymm güncellendi.");
		    		try {
		    			Follower.stepReduce();
		    			showPage(ymm.getMukellef());
		    		} catch(Exception e) {
		    			e.printStackTrace();
		    		}
		    	} else {
		    		Notifier.passMessage(Alert.AlertType.ERROR,
		    						"Hata.",
		    						"",
		    						"Ymm guncellenemedi.");
		    	}
        } else {
            Optional<ButtonType> result = Notifier.passMessage(Alert.AlertType.CONFIRMATION,
                    "Uyarı.",
                    "",
                    "Boş alanlar mevcut. Devam etmek ister misiniz?");
            if (result.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                postingChecker.setSpecialConditionFlag(true);
                doUpdate(ymm);
            }
        }
    }
}
