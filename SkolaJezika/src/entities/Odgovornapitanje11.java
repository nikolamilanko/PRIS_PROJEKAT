package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ODGOVORNAPITANJE11 database table.
 * 
 */
@Entity
@NamedQuery(name="Odgovornapitanje11.findAll", query="SELECT o FROM Odgovornapitanje11 o")
@Table(name="ODGOVORNAPITANJE11")
public class Odgovornapitanje11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idodgovoranapitanje;

	private byte jetacan;

	private String tekstodgovora;

	//bi-directional many-to-one association to Pitanje11
	@ManyToOne
	@JoinColumn(name="IDPITANJA")
	private Pitanje11 pitanje11;

	public Odgovornapitanje11() {
	}

	public int getIdodgovoranapitanje() {
		return this.idodgovoranapitanje;
	}

	public void setIdodgovoranapitanje(int idodgovoranapitanje) {
		this.idodgovoranapitanje = idodgovoranapitanje;
	}

	public byte getJetacan() {
		return this.jetacan;
	}

	public void setJetacan(byte jetacan) {
		this.jetacan = jetacan;
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

}