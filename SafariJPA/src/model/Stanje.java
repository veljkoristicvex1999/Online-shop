package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Stanje database table.
 * 
 */
@Entity
@NamedQuery(name="Stanje.findAll", query="SELECT s FROM Stanje s")
public class Stanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStanje;

	private String opis;

	//bi-directional many-to-one association to Predmet
	@OneToMany(mappedBy="stanje")
	private List<Predmet> predmets;

	public Stanje() {
	}

	public int getIdStanje() {
		return this.idStanje;
	}

	public void setIdStanje(int idStanje) {
		this.idStanje = idStanje;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Predmet> getPredmets() {
		return this.predmets;
	}

	public void setPredmets(List<Predmet> predmets) {
		this.predmets = predmets;
	}

	public Predmet addPredmet(Predmet predmet) {
		getPredmets().add(predmet);
		predmet.setStanje(this);

		return predmet;
	}

	public Predmet removePredmet(Predmet predmet) {
		getPredmets().remove(predmet);
		predmet.setStanje(null);

		return predmet;
	}

}