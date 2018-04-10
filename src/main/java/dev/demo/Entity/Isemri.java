package dev.demo.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bilalsay on 21/09/2017.
 */


@Entity
public class Isemri extends AncestorEntity {

	@GeneratedValue(strategy = GenerationType.AUTO) 
    @Id
    private int id;
    private String isemriNo;
    private Date isemriTarih;
    private String konu;
    private String tarhVergiDaire;
    private Date isemriYayinTarih;
    private Date isemriTamamTarih;
    private int incelemeTur;
    private String incelemeYil;
    private int durum;
    @ManyToOne
    @JoinColumn(name = "mukellef_id")
    private Mukellef mukellef;
    @OneToMany( mappedBy = "isemri")
    private Set<Note> noteSet = new HashSet<>();
    @OneToMany(mappedBy = "isemri")
    private Set<Islem> islemSet = new HashSet<>();
    @OneToMany(mappedBy = "isemri")
    private Set<Mulakat> mulakatSet = new HashSet<>();

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getTarhVergiDaire() {
        return tarhVergiDaire;
    }

    public void setTarhVergiDaire(String tarhVergiDaire) {
        this.tarhVergiDaire = tarhVergiDaire;
    }

    public Date getIsemriTamamTarih() {
        return isemriTamamTarih;
    }

    public void setIsemriTamamTarih(Date isemriTamamTarih) {
        this.isemriTamamTarih = isemriTamamTarih;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }
    
    public String getIsemriNo() {
        return isemriNo;
    }

    public void setIsemriNo(String isemriNo) {
        this.isemriNo = isemriNo;
    }

    public Date getIsemriTarih() {
        return isemriTarih;
    }

    public void setIsemriTarih(Date isemriTarih) {
        this.isemriTarih = isemriTarih;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public Date getIsemriYayinTarih() {
        return isemriYayinTarih;
    }

    public void setIsemriYayinTarih(Date isemriYayinTarih) {
        this.isemriYayinTarih = isemriYayinTarih;
    }

    public int getIncelemeTur() {
        return incelemeTur;
    }

    public void setIncelemeTur(int incelemeTur) {
        this.incelemeTur = incelemeTur;
    }

    public String getIncelemeYil() {
        return incelemeYil;
    }

    public void setIncelemeYil(String incelemeYil) {
        this.incelemeYil = incelemeYil;
    }

    public Mukellef getMukellef() {
        return mukellef;
    }

    public void setMukellef(Mukellef mukellef) {
        this.mukellef = mukellef;
    }

	public Set<Note> getNoteSet() {
		return noteSet;
	}

	public void setNoteSet(Set<Note> noteSet) {
		this.noteSet = noteSet;
	}

	public Set<Islem> getIslemSet() {
		return islemSet;
	}

	public void setIslemSet(Set<Islem> islemSet) {
		this.islemSet = islemSet;
	}

	public Set<Mulakat> getMulakatSet() {
		return mulakatSet;
	}

	public void setMulakatSet(Set<Mulakat> mulakatSet) {
		this.mulakatSet = mulakatSet;
	}
	
	
    
}