package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Predmet database table.
 * 
 */
@Entity
@NamedQuery(name="Predmet.findAll", query="SELECT p FROM Predmet p")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPredmet;

	private String naziv;

	private String opis;

	private int pocetnaCena;
	
	@Temporal(TemporalType.DATE)
	private Date rokZavAuk;

	private String slikaUrl;

	//bi-directional many-to-one association to Licitacija
	@OneToMany(mappedBy="predmet")
	private List<Licitacija> licitacijas;

	//bi-directional many-to-one association to Stanje
	@ManyToOne
	private Stanje stanje;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	private Kategorija kategorija;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Predmet() {
	}

	public int getIdPredmet() {
		return this.idPredmet;
	}

	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getPocetnaCena() {
		return this.pocetnaCena;
	}

	public void setPocetnaCena(int pocetnaCena) {
		this.pocetnaCena = pocetnaCena;
	}

	public Date getRokZavAuk() {
		return this.rokZavAuk;
	}

	public void setRokZavAuk(Date rokZavAuk) {
		this.rokZavAuk = rokZavAuk;
	}

	public String getSlikaUrl() {
		return this.slikaUrl;
	}

	public void setSlikaUrl(String slikaUrl) {
		this.slikaUrl = slikaUrl;
	}

	public List<Licitacija> getLicitacijas() {
		return this.licitacijas;
	}

	public void setLicitacijas(List<Licitacija> licitacijas) {
		this.licitacijas = licitacijas;
	}

	public Licitacija addLicitacija(Licitacija licitacija) {
		getLicitacijas().add(licitacija);
		licitacija.setPredmet(this);

		return licitacija;
	}

	public Licitacija removeLicitacija(Licitacija licitacija) {
		getLicitacijas().remove(licitacija);
		licitacija.setPredmet(null);

		return licitacija;
	}

	public Stanje getStanje() {
		return this.stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}