package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Licitacija database table.
 * 
 */
@Entity
@NamedQuery(name="Licitacija.findAll", query="SELECT l FROM Licitacija l")
public class Licitacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLicitacija;

	private double iznos;

	//bi-directional many-to-one association to Predmet
	@ManyToOne
	private Predmet predmet;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Licitacija() {
	}

	public int getIdLicitacija() {
		return this.idLicitacija;
	}

	public void setIdLicitacija(int idLicitacija) {
		this.idLicitacija = idLicitacija;
	}

	public double getIznos() {
		return this.iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}