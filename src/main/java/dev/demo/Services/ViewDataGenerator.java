package dev.demo.Services;

import dev.demo.Controller.CrudController;
import dev.demo.Controller.IsemriController;
import dev.demo.Controller.MukellefController;
import dev.demo.Entity.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import dev.demo.Services.SpecialEnums.*;

/**
 * Created by bilalsay on 31/10/2017.
 */
public class ViewDataGenerator {

    public static ObservableList<MyHBox> mukellefShowDataGenerate(Mukellef mukellef) {
        return  FXCollections.observableArrayList(
                new MyHBox().addElements(new TitleLabel("Unvanı/Adı Soyadı "), new MTextField(mukellef.getUnvan())),
                new MyHBox().addElements(new TitleLabel("Vergi Kimlik No "), new 	MTextField(mukellef.getMukellefVergiKimlikNo())),
                new MyHBox().addElements(new TitleLabel("TC Kimlik No "), new MTextField(mukellef.getTcKimlikNo())),
                new MyHBox().addElements(new TitleLabel("Vergi Dairesi "), new MTextField(mukellef.getVergiDaire())),
				new MyHBox().addElements(new TitleLabel("Faaliyet Konusu "), new MTextField(mukellef.getFaaliyetKonu())),
                new MyHBox().addElements(new TitleLabel("Faaliyet Durumu "), new MTextField(mukellef.getFaaliyetDurum() == 0 ? TextEnum.TERK.toString() + " / " + new SimpleDateFormat("dd.MM.yyyy").format(mukellef.getTerkTarih()) : TextEnum.FAAL.toString())),
                new MyHBox().addElements(new TitleLabel("Email "), new MTextField(mukellef.getEmail())),
                new MyHBox().addElements(new TitleLabel("Telefon "), new MTextField(mukellef.getTelefonNo())),
                new MyHBox().addElements(new TitleLabel("GSM Telefon "), new MTextField(mukellef.getGsmTelefonNo())),
                new MyHBox().addElements(new TitleLabel("İkametgah Adresi "), new MTextField(mukellef.getIkametgahAdres())),
                new MyHBox().addElements(new TitleLabel("İş Yeri Adresi "), new MTextField(mukellef.getIsYeriAdres())),
                new MyHBox().addElements(new TitleLabel("Kayıt Tarihi "), new MTextField(new SimpleDateFormat("dd.MM.yyyy").format(mukellef.getKayitTarih())))
        );
    }

	public static ObservableList<MyHBox> temsilciShowDataGenerate(Temsilci temsilci) {
		return  FXCollections.observableArrayList(
				new MyHBox().addElements(new TitleLabel("Adı Soyadı "), new MTextField(temsilci.getAdSoyad())),
				new MyHBox().addElements(new TitleLabel("TC Kimlik No "), new MTextField(temsilci.getTcKimlikNo())),
				new MyHBox().addElements(new TitleLabel("Email "), new MTextField(temsilci.getEmail())),
				new MyHBox().addElements(new TitleLabel("Telefon "), new MTextField(temsilci.getTelefonNo())),
				new MyHBox().addElements(new TitleLabel("İkametgah Adresi "), new MTextField(temsilci.getIkametgahAdres()))
		);
	}
    
    public static ObservableList<ContainerBox> noteDataGenerate(CrudController controller, List<Note> list) {
	    	ObservableList<ContainerBox> observableList = FXCollections.observableArrayList();
	    	for(Note note: list) {
	    		try {
	    			observableList.add(containerHBoxGenerate(controller, note));
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
    		return observableList;
    }
    
    public static ObservableList<ContainerBox> islemDataGenerate(CrudController controller, List<Islem> list) {
	    	ObservableList<ContainerBox> observableList = FXCollections.observableArrayList();
	    	for(Islem islem: list) {
	    		try {
	    			observableList.add(containerHBoxGenerate(controller, islem));
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	return observableList;
    }
    
    public static ObservableList<ContainerBox> mulakatDataGenerate(CrudController controller, List<Mulakat> list) {
	    	ObservableList<ContainerBox> observableList = FXCollections.observableArrayList();
	    	for(Mulakat mulakat: list) {
	    		try {
	    			observableList.add(containerHBoxGenerate(controller, mulakat));
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    	}
		return observableList;
    }
    
    public static ContainerBox containerHBoxGenerate(CrudController controller, Note note) throws Exception {
    		ContainerBox containerBox = new ContainerBox(new NoteInnerTopBox(controller, note));
    		containerBox.setId(String.valueOf(note.getId()));
    		containerBox.textData.setText(note.getIcerik());
    		EventGenerator.generateEventsTopBoxOfContainerBox(controller, note, containerBox);
    		return containerBox;
    }
    
    public static ContainerBox containerHBoxGenerate(CrudController controller, Islem islem) throws Exception {
	    	ContainerBox containerBox = new ContainerBox(new IslemInnerTopBox(controller, islem));
	    	containerBox.setId(String.valueOf(islem.getId()));
	    	containerBox.textData.setText(islem.getIcerik());
	    	EventGenerator.generateEventsTopBoxOfContainerBox(controller, islem, containerBox);
	    	return containerBox;
    }
    
    public static ContainerBox containerHBoxGenerate(CrudController controller, Mulakat mulakat) throws Exception {
		ContainerBox containerBox = new ContainerBox(new MulakatInnerTopBox(controller, mulakat));
		containerBox.setId(String.valueOf(mulakat.getId()));
		containerBox.textData.setText(mulakat.getIcerik());
		EventGenerator.generateEventsTopBoxOfContainerBox(controller, mulakat, containerBox);
		return containerBox;
}

    public static ObservableList<MyHBox> isemriShowDataGenerate(Isemri isemri) {
        return  FXCollections.observableArrayList(
                new MyHBox().addElements(new TitleLabel("No ").setColor("#FF0000"), new MTextField(isemri.getIsemriNo())),
				new MyHBox().addElements(new TitleLabel("İş Emri Tarihi ").setColor("#FF0000"), new MTextField(new SimpleDateFormat("dd.MM.yyyy").format(isemri.getIsemriTarih()))),
                new MyHBox().addElements(new TitleLabel("Tarh Vergi Dairesi ").setColor("#FF0000"), new MTextField(isemri.getTarhVergiDaire())),
                new MyHBox().addElements(new TitleLabel("Konusu ").setColor("#FF0000"), new MTextField(isemri.getKonu())),
                new MyHBox().addElements(new TitleLabel("İnceleme Türü ").setColor("#FF0000"), new MTextField((isemri.getIncelemeTur() == 0 ? TextEnum.SINIRLI.toString() : TextEnum.TAM.toString()))),
                new MyHBox().addElements(new TitleLabel("İnceleme Yılı ").setColor("#FF0000"), new MTextField(isemri.getIncelemeYil())),
                new MyHBox().addElements(new TitleLabel("Yayın Tarihi ").setColor("#FF0000"), new MTextField(new SimpleDateFormat("dd.MM.yyyy").format(isemri.getIsemriYayinTarih()))),
                new MyHBox().addElements(new TitleLabel("Bitiş Tarihi ").setColor("#FF0000"), new MTextField(new SimpleDateFormat("dd.MM.yyyy").format(isemri.getIsemriTamamTarih()))),
                new MyHBox().addElements(new TitleLabel("Kalan Gün ").setColor("#FF0000"), new MTextField(" "+ Math.round((isemri.getIsemriTamamTarih().getTime() - Date.valueOf(LocalDate.now()).getTime()) / (double) 86400000)+ " ")),
				new MyHBox().addElements(new TitleLabel("Durum ").setColor("#FF0000"), new MTextField((isemri.getDurum() == 0 ? TextEnum.DEVAM.toString() : TextEnum.TAMAM.toString())))
        );
    }

    public static ObservableList<MyHBox> smmShowDataGenerate(Smm smm) {
        return  FXCollections.observableArrayList(
                new MyHBox().addElements(new TitleLabel("Adı Soyadi ").setColor("#04B45F"), new MTextField(smm.getAdSoyad())),
                new MyHBox().addElements(new TitleLabel("Telefon ").setColor("#04B45F"), new MTextField(smm.getTelefonNo())),
                new MyHBox().addElements(new TitleLabel("Email ").setColor("#04B45F"), new MTextField(smm.getEmail()))
        );
    }
    
    public static ObservableList<MyHBox> ymmShowDataGenerate(Ymm ymm) {
    	return  FXCollections.observableArrayList(
    			new MyHBox().addElements(new TitleLabel("Adı Soyadı ").setColor("#04B45F"), new MTextField(ymm.getAdSoyad())),
    			new MyHBox().addElements(new TitleLabel("Telefon ").setColor("#04B45F"), new MTextField(ymm.getTelefonNo())),
    			new MyHBox().addElements(new TitleLabel("Email ").setColor("#04B45F"), new MTextField(ymm.getEmail()))
    			);
    }
    
    public static void mukellefListDataGenerate(MukellefController mukellefController) {
    		mukellefController.mukellefTitleTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getUnvan());
			});
	    	mukellefController.vergiKimlikNoTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(cellData.getValue().getMukellefVergiKimlikNo()); 
	    		});
	    	mukellefController.vergiDaireTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(cellData.getValue().getVergiDaire());
	    		});
	    	mukellefController.telefonNoTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(cellData.getValue().getTelefonNo()); 
	    	});
	    	mukellefController.emailTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(cellData.getValue().getEmail());
	    		});
	    	mukellefController.isYeriAdresTC.setCellValueFactory(cellData -> {
	    		return new ReadOnlyStringWrapper(cellData.getValue().getIsYeriAdres());
	    		});
	    	mukellefController.ikametgahAdresTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(cellData.getValue().getIkametgahAdres());
	    		});
	    	mukellefController.eklenmeTarihTC.setCellValueFactory(cellData -> { 
	    		return new ReadOnlyStringWrapper(new SimpleDateFormat("dd.MM.yyyy").format(cellData.getValue().getKayitTarih()));
	    		});
    }
    
    public static void isemriListDataGenerate(IsemriController isemriController) {
	    	isemriController.mukellefVergiDaireTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getMukellef().getVergiDaire());
			});
	    	isemriController.isemriNoTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getIsemriNo());
			});
	    	isemriController.mukellefAdSoyadTC.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getMukellef().getUnvan());
			});
	    	isemriController.tarhVergiDaireTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getTarhVergiDaire());
			});
	    	isemriController.incelemeTurTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getIncelemeTur() == 0 ? TextEnum.SINIRLI.toString() : TextEnum.TAM.toString());
			});
	    	isemriController.konuTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getKonu());
			});
	    	isemriController.incelemeYilTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(cellData.getValue().getIncelemeYil());
			});
	    	isemriController.isemriYayinTarihTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(
						new SimpleDateFormat("dd.MM.yyyy").format(cellData.getValue().getIsemriYayinTarih()));
			});
			isemriController.isemriTamamTarihTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(
					new SimpleDateFormat("dd.MM.yyyy").format(cellData.getValue().getIsemriTamamTarih()));
			});
	    	isemriController.durumTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper((cellData.getValue().getDurum() == 0 ? TextEnum.DEVAM.toString() : TextEnum.TAMAM.toString()));
			});
			isemriController.kalanGunTC.setCellValueFactory(cellData -> {
				return new ReadOnlyStringWrapper(String.valueOf(Math.round((cellData.getValue().getIsemriTamamTarih().getTime() - Date.valueOf(LocalDate.now()).getTime()) / (double) 86400000)));
			});
    }
    
    public static void mukellefUpdateFormDataGenerate(MukellefController mukellefController, Mukellef mukellef) {
	    	mukellefController.title.setText(mukellef.getUnvan());
	    	mukellefController.vergiKimlikNoTF.setText(mukellef.getMukellefVergiKimlikNo());
	    	mukellefController.tcKimlikNoTF.setText(mukellef.getTcKimlikNo());
			mukellefController.unvanTF.setText(mukellef.getUnvan());
	        mukellefController.vergiDaireCB.valueProperty().setValue(mukellef.getVergiDaire());
	        mukellefController.faaliyetDurumCB.valueProperty().setValue((mukellef.getFaaliyetDurum() == 0 ? TextEnum.TERK : TextEnum.FAAL));
	        mukellefController.faaliyetKonuTF.setText(mukellef.getFaaliyetKonu());
	        if (mukellef.getFaaliyetDurum() == 0) {
		        	mukellefController.terkTarihInput.setVisible(true);
		        	mukellefController.terkTarihInput.getChildren().add(0, mukellefController.terkTarihLabel);
		        	mukellefController.terkTarihDP.setValue(mukellef.getTerkTarih().toLocalDate());
		        	mukellefController.terkTarihInput.getChildren().add(1, mukellefController.terkTarihDP);
	        }
	        mukellefController.telefonTF.setText(mukellef.getTelefonNo());
	        mukellefController.gsmTelefonTF.setText(mukellef.getGsmTelefonNo());
	        mukellefController.emailTF.setText(mukellef.getEmail());
	        mukellefController.isYeriAdresTA.setText(mukellef.getIsYeriAdres());
	        mukellefController.ikametgahAdresTA.setText(mukellef.getIkametgahAdres());
    }
    
    public static void temsilciUpdateFormDataGenerate(MukellefController mukellefController, Temsilci temsilci) {
	    	mukellefController.title.setText(temsilci.getAdSoyad() + "(M:" + temsilci.getMukellef().getUnvan() + ")");
	    	mukellefController.temsilciAdSoyadTF.setText(temsilci.getAdSoyad());
	    	mukellefController.temsilciTcKimlikNoTF.setText(temsilci.getTcKimlikNo());
	    	mukellefController.temsilciTelefonTF.setText(temsilci.getTelefonNo());
	    	mukellefController.temsilciEmailTF.setText(temsilci.getEmail());
	    	mukellefController.temsilciIkametgahAdresTA.setText(temsilci.getIkametgahAdres());
    }

    public static void smmUpdateFormDataGenerate(MukellefController mukellefController, Smm smm) {
	    	mukellefController.title.setText(smm.getAdSoyad() + "(M:" + smm.getMukellef().getUnvan() + ")");
	    	mukellefController.smmAdSoyadTF.setText(smm.getAdSoyad());
	    	mukellefController.smmTelefonTF.setText(smm.getTelefonNo());
	    	mukellefController.smmEmailTF.setText(smm.getEmail());
    }
    
    public static void ymmUpdateFormDataGenerate(MukellefController mukellefController, Ymm ymm) {
    	mukellefController.title.setText(ymm.getAdSoyad() + "(M:" + ymm.getMukellef().getUnvan() + ")");
    	mukellefController.ymmAdSoyadTF.setText(ymm.getAdSoyad());
    	mukellefController.ymmTelefonTF.setText(ymm.getTelefonNo());
    	mukellefController.ymmEmailTF.setText(ymm.getEmail());
    }

	public static void isemriUpdateFormDataGenerate(IsemriController controller, Isemri isemri) {
		controller.title.setText(isemri.getIsemriNo());
		controller.isEmriNoTF.setText(isemri.getIsemriNo());
		controller.isEmriDefaultTarihDP.setValue(isemri.getIsemriTarih().toLocalDate());
		controller.isEmriKonuTA.setText(isemri.getKonu());
		controller.tarhVergiDaireTF.setText(isemri.getTarhVergiDaire());
		controller.isEmriTarihDP.setValue(isemri.getIsemriTamamTarih().toLocalDate());
		controller.vergiKimlikNoCB.valueProperty().setValue(isemri.getMukellef());
		controller.incelemeYilTF.setText(isemri.getIncelemeYil());
		controller.incelemeTurCB.valueProperty().setValue((isemri.getIncelemeTur() == 0 ? TextEnum.SINIRLI : TextEnum.TAM));
		controller.durumCB.valueProperty().setValue((isemri.getDurum() == 0 ? TextEnum.DEVAM : TextEnum.TAMAM));
	}

	public static void isemriAddAndUpdateComboboxDataGenerate(IsemriController controller, List<Mukellef> mukellefList) {
		for (Mukellef m : mukellefList) {
			controller.vergiKimlikNoCB.getItems().add(m);
		}
		controller.isEmriTarihDP.setValue(LocalDate.now());
		controller.isEmriDefaultTarihDP.setValue(LocalDate.now());
		controller.vergiKimlikNoCB.setPromptText("Listeden Mükellef Seçin");
		controller.vergiKimlikNoCB.valueProperty().addListener((obs, oldVal, newVal) -> {
			controller.vergiKimlikNoCB.setId(String.valueOf(newVal.getMukellefId()));
		});
	}

}
