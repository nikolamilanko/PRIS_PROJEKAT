package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ISHOD11 database table.
 * 
 */
@Entity
@NamedQuery(name="Ishod11.findAll", query="SELECT i FROM Ishod11 i")
@Table(name="ISHOD11")
public class Ishod11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idishoda;

	private byte jepolozio;

	//bi-directional many-to-one association to Kurs11
	@ManyToOne
	@JoinColumn(name="IDKURSA")
	private Kurs11 kurs11;

	//bi-directional many-to-one association to Polaznik11
	@ManyToOne
	@JoinColumn(name="IDPOLAZNIKA")
	private Polaznik11 polaznik11;

	public Ishod11() {
	}

	public int getIdishoda() {
		return this.idishoda;
	}

	public void setIdishoda(int idishoda) {
		this.idishoda = idishoda;
	}

	public byte getJepolozio() {
		return this.jepolozio;
	}

	public void setJepolozio(byte jepolozio) {
		this.jepolozio = jepolozio;
	}

	public Kurs11 getKurs11() {
		return this.kurs11;
	}

	public void setKurs11(Kurs11 kurs11) {
		this.kurs11 = kurs11;
	}

	public Polaznik11 getPolaznik11() {
		return this.polaznik11;
	}

	public void setPolaznik11(Polaznik11 polaznik11) {
		this.polaznik11 = polaznik11;
	}

}