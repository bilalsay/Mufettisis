package dev.demo.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by bilalsay on 21/09/2017.
 */


@Entity
public class Mukellef extends AncestorEntity {

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int mukellefId;
    private String mukellefVergiKimlikNo;
    private String tcKimlikNo;
    private String unvan;
    private String vergiDaire;
    private int faaliyetDurum;
    private String faaliyetKonu;
    private String telefonNo;
    private String gsmTelefonNo;
    private String email;
    private String isYeriAdres;
    private String ikametgahAdres;
    private Date kayitTarih;
    private Date terkTarih;
    @OneToMany(mappedBy = "mukellef")
    private Set<Isemri> isemriSet;
    @OneToOne(mappedBy = "mukellef")
    private Temsilci temsilci;
    @OneToOne(mappedBy = "mukellef")
    private Smm smm;
    @OneToOne(mappedBy = "mukellef")
    private Ymm ymm;
    
    public int getMukellefId() {
		return mukellefId;
	}

	public void setMukellefId(int mukellefId) {
		this.mukellefId = mukellefId;
	}

	public String getMukellefVergiKimlikNo() {
        return mukellefVergiKimlikNo;
    }

    public void setMukellefVergiKimlikNo(String mukellefVergiKimlikNo) {
        this.mukellefVergiKimlikNo = mukellefVergiKimlikNo;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getVergiDaire() {
        return vergiDaire;
    }

    public void setVergiDaire(String vergiDaire) {
        this.vergiDaire = vergiDaire;
    }

    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsYeriAdres() {
        return isYeriAdres;
    }

    public void setIsYeriAdres(String isYeriAdres) {
        this.isYeriAdres = isYeriAdres;
    }

    public String getIkametgahAdres() {
        return ikametgahAdres;
    }

    public void setIkametgahAdres(String ikametgahAdres) {
        this.ikametgahAdres = ikametgahAdres;
    }

    public Date getKayitTarih() {
        return kayitTarih;
    }

    public void setKayitTarih(Date kayitTarih) {
        this.kayitTarih = kayitTarih;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public int getFaaliyetDurum() {
        return faaliyetDurum;
    }

    public void setFaaliyetDurum(int faaliyetDurum) {
        this.faaliyetDurum = faaliyetDurum;
    }

    public String getFaaliyetKonu() {
        return faaliyetKonu;
    }

    public void setFaaliyetKonu(String faaliyetKonu) {
        this.faaliyetKonu = faaliyetKonu;
    }

    public String getGsmTelefonNo() {
        return gsmTelefonNo;
    }

    public void setGsmTelefonNo(String gsmTelefonNo) {
        this.gsmTelefonNo = gsmTelefonNo;
    }

    public Date getTerkTarih() {
        return terkTarih;
    }

    public void setTerkTarih(Date terkTarih) {
        this.terkTarih = terkTarih;
    }

    public Temsilci getTemsilci() {
        return temsilci;
    }

    public void setTemsilci(Temsilci temsilci) {
        this.temsilci = temsilci;
    }

    public Smm getSmm() {
        return smm;
    }

    public void setSmm(Smm smm) {
        this.smm = smm;
    }

    public Ymm getYmm() {
		return ymm;
	}

	public void setYmm(Ymm ymm) {
		this.ymm = ymm;
	}

	@Override
    public String toString() {
        return getUnvan();
    }
}