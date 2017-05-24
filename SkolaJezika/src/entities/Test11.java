package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TEST11 database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Test11.findAll", query="SELECT t FROM Test11 t")
})
@Table(name="TEST11")
public class Test11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtesta;

	private int brpoenatesta;

	@Temporal(TemporalType.DATE)
	private Date datumtesta;

	private String naslovtesta;

	//bi-directional many-to-one association to Pitanje11
	@OneToMany(mappedBy="test11")
	private List<Pitanje11> pitanje11s;

	//bi-directional many-to-one association to Rezultat11
	@OneToMany(mappedBy="test11")
	private List<Rezultat11> rezultat11s;

	//bi-directional many-to-one association to Lekcija11
	@ManyToOne
	@JoinColumn(name="IDLEKCIJE")
	private Lekcija11 lekcija11;

	public Test11() {
	}

	public int getIdtesta() {
		return this.idtesta;
	}

	public void setIdtesta(int idtesta) {
		this.idtesta = idtesta;
	}

	public int getBrpoenatesta() {
		return this.brpoenatesta;
	}

	public void setBrpoenatesta(int brpoenatesta) {
		this.brpoenatesta = brpoenatesta;
	}

	public Date getDatumtesta() {
		return this.datumtesta;
	}

	public void setDatumtesta(Date datumtesta) {
		this.datumtesta = datumtesta;
	}

	public String getNaslovtesta() {
		return this.naslovtesta;
	}

	public void setNaslovtesta(String naslovtesta) {
		this.naslovtesta = naslovtesta;
	}

	public List<Pitanje11> getPitanje11s() {
		return this.pitanje11s;
	}

	public void setPitanje11s(List<Pitanje11> pitanje11s) {
		this.pitanje11s = pitanje11s;
	}

	public Pitanje11 addPitanje11(Pitanje11 pitanje11) {
		getPitanje11s().add(pitanje11);
		pitanje11.setTest11(this);

		return pitanje11;
	}

	public Pitanje11 removePitanje11(Pitanje11 pitanje11) {
		getPitanje11s().remove(pitanje11);
		pitanje11.setTest11(null);

		return pitanje11;
	}

	public List<Rezultat11> getRezultat11s() {
		return this.rezultat11s;
	}

	public void setRezultat11s(List<Rezultat11> rezultat11s) {
		this.rezultat11s = rezultat11s;
	}

	public Rezultat11 addRezultat11(Rezultat11 rezultat11) {
		getRezultat11s().add(rezultat11);
		rezultat11.setTest11(this);

		return rezultat11;
	}

	public Rezultat11 removeRezultat11(Rezultat11 rezultat11) {
		getRezultat11s().remove(rezultat11);
		rezultat11.setTest11(null);

		return rezultat11;
	}

	public Lekcija11 getLekcija11() {
		return this.lekcija11;
	}

	public void setLekcija11(Lekcija11 lekcija11) {
		this.lekcija11 = lekcija11;
	}

}