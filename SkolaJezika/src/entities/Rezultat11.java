package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REZULTAT11 database table.
 * 
 */
@Entity
@NamedQuery(name="Rezultat11.findAll", query="SELECT r FROM Rezultat11 r")
@Table(name="REZULTAT11")
public class Rezultat11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrezultata;

	private int brpoena;

	private byte polozio;

	//bi-directional many-to-one association to Test11
	@ManyToOne
	@JoinColumn(name="IDTESTA")
	private Test11 test11;

	//bi-directional many-to-one association to Polaznik11
	@ManyToOne
	@JoinColumn(name="IDPOLAZNIKA")
	private Polaznik11 polaznik11;

	public Rezultat11() {
	}

	public int getIdrezultata() {
		return this.idrezultata;
	}

	public void setIdrezultata(int idrezultata) {
		this.idrezultata = idrezultata;
	}

	public int getBrpoena() {
		return this.brpoena;
	}

	public void setBrpoena(int brpoena) {
		this.brpoena = brpoena;
	}

	public byte getPolozio() {
		return this.polozio;
	}

	public void setPolozio(byte polozio) {
		this.polozio = polozio;
	}

	public Test11 getTest11() {
		return this.test11;
	}

	public void setTest11(Test11 test11) {
		this.test11 = test11;
	}

	public Polaznik11 getPolaznik11() {
		return this.polaznik11;
	}

	public void setPolaznik11(Polaznik11 polaznik11) {
		this.polaznik11 = polaznik11;
	}

}