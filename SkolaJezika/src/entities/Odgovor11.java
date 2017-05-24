package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ODGOVOR11 database table.
 * 
 */
@Entity
@NamedQuery(name="Odgovor11.findAll", query="SELECT o FROM Odgovor11 o")
public class Odgovor11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idodgovora;

	private String jepregledan;

	private String obelezeniiodogovara;

	private String tekstodgovora;

	//bi-directional many-to-one association to Pitanje11
	@ManyToOne
	@JoinColumn(name="IDPITANJA")
	private Pitanje11 pitanje11;

	//bi-directional many-to-one association to Polaznik11
	@ManyToOne
	@JoinColumn(name="IDPOLAZNIKA")
	private Polaznik11 polaznik11;

	public Odgovor11() {
	}

	public int getIdodgovora() {
		return this.idodgovora;
	}

	public void setIdodgovora(int idodgovora) {
		this.idodgovora = idodgovora;
	}

	public String getJepregledan() {
		return this.jepregledan;
	}

	public void setJepregledan(String jepregledan) {
		this.jepregledan = jepregledan;
	}

	public String getObelezeniiodogovara() {
		return this.obelezeniiodogovara;
	}

	public void setObelezeniiodogovara(String obelezeniiodogovara) {
		this.obelezeniiodogovara = obelezeniiodogovara;
	}

	public String getTekstodgovora() {
		return this.tekstodgovora;
	}

	public void setTekstodgovora(String tekstodgovora) {
		this.tekstodgovora = tekstodgovora;
	}

	public Pitanje11 getPitanje11() {
		return this.pitanje11;
	}

	public void setPitanje11(Pitanje11 pitanje11) {
		this.pitanje11 = pitanje11;
	}

	public Polaznik11 getPolaznik11() {
		return this.polaznik11;
	}

	public void setPolaznik11(Polaznik11 polaznik11) {
		this.polaznik11 = polaznik11;
	}

}