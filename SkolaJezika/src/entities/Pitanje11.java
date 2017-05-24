package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PITANJE11 database table.
 * 
 */
@Entity
@NamedQuery(name="Pitanje11.findAll", query="SELECT p FROM Pitanje11 p")
@Table(name="PITANJE11")
public class Pitanje11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpitanja;

	private String tekstpitanja;

	//bi-directional many-to-one association to Odgovor11
	@OneToMany(mappedBy="pitanje11")
	private List<Odgovor11> odgovor11s;

	//bi-directional many-to-one association to Odgovornapitanje11
	@OneToMany(mappedBy="pitanje11")
	private List<Odgovornapitanje11> odgovornapitanje11s;

	//bi-directional many-to-one association to Test11
	@ManyToOne
	@JoinColumn(name="IDTESTA")
	private Test11 test11;

	public Pitanje11() {
	}

	public int getIdpitanja() {
		return this.idpitanja;
	}

	public void setIdpitanja(int idpitanja) {
		this.idpitanja = idpitanja;
	}

	public String getTekstpitanja() {
		return this.tekstpitanja;
	}

	public void setTekstpitanja(String tekstpitanja) {
		this.tekstpitanja = tekstpitanja;
	}

	public List<Odgovor11> getOdgovor11s() {
		return this.odgovor11s;
	}

	public void setOdgovor11s(List<Odgovor11> odgovor11s) {
		this.odgovor11s = odgovor11s;
	}

	public Odgovor11 addOdgovor11(Odgovor11 odgovor11) {
		getOdgovor11s().add(odgovor11);
		odgovor11.setPitanje11(this);

		return odgovor11;
	}

	public Odgovor11 removeOdgovor11(Odgovor11 odgovor11) {
		getOdgovor11s().remove(odgovor11);
		odgovor11.setPitanje11(null);

		return odgovor11;
	}

	public List<Odgovornapitanje11> getOdgovornapitanje11s() {
		return this.odgovornapitanje11s;
	}

	public void setOdgovornapitanje11s(List<Odgovornapitanje11> odgovornapitanje11s) {
		this.odgovornapitanje11s = odgovornapitanje11s;
	}

	public Odgovornapitanje11 addOdgovornapitanje11(Odgovornapitanje11 odgovornapitanje11) {
		getOdgovornapitanje11s().add(odgovornapitanje11);
		odgovornapitanje11.setPitanje11(this);

		return odgovornapitanje11;
	}

	public Odgovornapitanje11 removeOdgovornapitanje11(Odgovornapitanje11 odgovornapitanje11) {
		getOdgovornapitanje11s().remove(odgovornapitanje11);
		odgovornapitanje11.setPitanje11(null);

		return odgovornapitanje11;
	}

	public Test11 getTest11() {
		return this.test11;
	}

	public void setTest11(Test11 test11) {
		this.test11 = test11;
	}

}