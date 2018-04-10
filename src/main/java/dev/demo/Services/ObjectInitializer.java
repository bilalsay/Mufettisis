package dev.demo.Services;

import java.sql.Date;
import java.time.LocalDate;

import dev.demo.Controller.IsemriController;
import dev.demo.Controller.IslemController;
import dev.demo.Controller.MukellefController;
import dev.demo.Controller.MulakatController;
import dev.demo.Controller.NoteController;
import dev.demo.Entity.*;
import dev.demo.Services.SpecialEnums.TextEnum;

public class ObjectInitializer {
	
	public static void mukellefInitialize(MukellefController mukellefControl, Mukellef mukellef, boolean updateFlag) {
        mukellef.setMukellefVergiKimlikNo(mukellefControl.vergiKimlikNoTF.getText().trim());
        mukellef.setTcKimlikNo(mukellefControl.tcKimlikNoTF.getText().trim());
        mukellef.setUnvan(mukellefControl.unvanTF.getText().trim());
        mukellef.setVergiDaire(mukellefControl.vergiDaireCB.getValue());
        mukellef.setFaaliyetDurum(TextEnum.FAAL == mukellefControl.faaliyetDurumCB.getValue() ? 1 : 0);
        mukellef.setFaaliyetKonu(mukellefControl.faaliyetKonuTF.getText().trim());
        mukellef.setTelefonNo(mukellefControl.telefonTF.getText().trim());
        mukellef.setGsmTelefonNo(mukellefControl.gsmTelefonTF.getText().trim());
        mukellef.setEmail(mukellefControl.emailTF.getText().trim());
        mukellef.setIsYeriAdres(mukellefControl.isYeriAdresTA.getText().trim());
        mukellef.setIkametgahAdres(mukellefControl.ikametgahAdresTA.getText().trim());
        mukellef.setKayitTarih(updateFlag == false ? Date.valueOf(LocalDate.now()) : mukellef.getKayitTarih());
        mukellef.setTerkTarih(mukellefControl.terkTarihDP.getValue() == null ? null : Date.valueOf(mukellefControl.terkTarihDP.getValue()));
	}
	
	public static void isemriInitialize(IsemriController isemriController, Isemri isemri, boolean updateFlag) {
		isemri.setIsemriNo(isemriController.isEmriNoTF.getText().trim());
		isemri.setIsemriTarih(Date.valueOf(isemriController.isEmriDefaultTarihDP.getValue()));
        isemri.setKonu(isemriController.isEmriKonuTA.getText().trim());
        isemri.setTarhVergiDaire(isemriController.tarhVergiDaireTF.getText().trim());
        isemri.setIsemriYayinTarih(updateFlag == false ? Date.valueOf(LocalDate.now()) : isemri.getIsemriYayinTarih());
        isemri.setIsemriTamamTarih(Date.valueOf(isemriController.isEmriTarihDP.getValue()));
        isemri.setIncelemeYil(isemriController.incelemeYilTF.getText().trim());
        isemri.setIncelemeTur((isemriController.incelemeTurCB.getValue() == TextEnum.SINIRLI ? 0 : 1));
        isemri.setDurum(updateFlag == false ? 0 : (isemriController.durumCB.getValue() == TextEnum.DEVAM ? 0 : 1));
	}
	
	public static void temsilciInitialize(MukellefController mukellefControl, Temsilci temsilci) {
		temsilci.setAdSoyad(mukellefControl.temsilciAdSoyadTF.getText().trim());
		temsilci.setTcKimlikNo(mukellefControl.temsilciTcKimlikNoTF.getText().trim());
		temsilci.setTelefonNo(mukellefControl.temsilciTelefonTF.getText().trim());
		temsilci.setEmail(mukellefControl.temsilciEmailTF.getText().trim());
		temsilci.setIkametgahAdres(mukellefControl.temsilciIkametgahAdresTA.getText().trim());
	}

	public static void smmInitialize(MukellefController mukellefControl, Smm smm) {
		smm.setAdSoyad(mukellefControl.smmAdSoyadTF.getText().trim());
        smm.setTelefonNo(mukellefControl.smmTelefonTF.getText().trim());
        smm.setEmail(mukellefControl.smmEmailTF.getText().trim());
	}
	
	public static void ymmInitialize(MukellefController mukellefControl, Ymm ymm) {
		ymm.setAdSoyad(mukellefControl.ymmAdSoyadTF.getText().trim());
		ymm.setTelefonNo(mukellefControl.ymmTelefonTF.getText().trim());
		ymm.setEmail(mukellefControl.ymmEmailTF.getText().trim());
	}

	public static void noteInitialize(NoteController noteControl, Note note) {
		note.setIcerik(noteControl.icerikTA.getText());
		note.setYayinTarih(Date.valueOf(LocalDate.now()));
		note.setKategori(noteControl.kategoriCB.getValue().getId());
		note.setDurum(1);
	}
	
	public static void islemInitialize(IslemController islemControl, Islem islem) {
		islem.setIcerik(islemControl.icerikTA.getText());
		islem.setYayinTarih(Date.valueOf(LocalDate.now()));
		islem.setKategori(islemControl.kategoriCB.getValue().getId());
		islem.setDurum(0);
	}
	
	public static void mulakatInitialize(MulakatController mulakatControl, Mulakat mulakat, boolean updateFlag) {
		mulakat.setIcerik(mulakatControl.icerikTA.getText());
		mulakat.setYayinTarih(updateFlag == false ? Date.valueOf(LocalDate.now()) : mulakat.getYayinTarih());
		mulakat.setKategori(mulakatControl.kategoriCB.getValue().getId());
		mulakat.setDurum(0);
	}

}
