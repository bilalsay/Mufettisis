package dev.demo.Entity;

import javax.persistence.*;

/**
 * Created by bilalsay on 21/09/2017.
 */

@Entity
public class Ymm extends AncestorEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;
    private String adSoyad;
    private String telefonNo;
    private String email;
    @OneToOne()
    @JoinColumn(name = "mukellef_id")
    private Mukellef mukellef;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
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

	public Mukellef getMukellef() {
		return mukellef;
	}

	public void setMukellef(Mukellef mukellef) {
		this.mukellef = mukellef;
	}
}