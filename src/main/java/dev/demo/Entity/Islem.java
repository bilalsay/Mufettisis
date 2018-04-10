package dev.demo.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by bilalsay on 21/09/2017.
 */


@Entity
public class Islem extends AncestorEntity {

	@GeneratedValue(strategy = GenerationType.AUTO) 
    @Id
    private int id;
    private int kategori;
    private String icerik;
    private Date yayinTarih;
    private Date tamamTarih;
    private int durum;
    @ManyToOne
    @JoinColumn(name = "isemri_id")
    private Isemri isemri;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Isemri getIsemri() {
        return isemri;
    }

    public void setIsemri(Isemri isemri) {
        this.isemri = isemri;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getYayinTarih() {
        return yayinTarih;
    }

    public void setYayinTarih(Date yayinTarih) {
        this.yayinTarih = yayinTarih;
    }

    public Date getTamamTarih() {
        return tamamTarih;
    }

    public void setTamamTarih(Date tamamTarih) {
        this.tamamTarih = tamamTarih;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }
}