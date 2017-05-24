package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the KOMENTAR11 database table.
 * 
 */
@Entity
@NamedQuery(name="Komentar11.findAll", query="SELECT k FROM Komentar11 k")
@Table(name="KOMENTAR11")
public class Komentar11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idkomentara;

	@Temporal(TemporalType.DATE)
	private Date datumkomentara;

	private String tekstkomentara;

	//bi-directional many-to-one association to Kurs11
	@ManyToOne
	@JoinColumn(name="IDKURSA")
	private Kurs11 kurs11;

	//bi-directional many-to-one association to Polaznik11
	@ManyToOne
	@JoinColumn(name="IDPOLAZNIKA")
	private Polaznik11 polaznik11;

	public Komentar11() {
	}

	public int getIdkomentara() {
		return this.idkomentara;
	}

	public void setIdkomentara(int idkomentara) {
		this.idkomentara = idkomentara;
	}

	public Date getDatumkomentara() {
		return this.datumkomentara;
	}

	public void setDatumkomentara(Date datumkomentara) {
		this.datumkomentara = datumkomentara;
	}

	public String getTekstkomentara() {
		return this.tekstkomentara;
	}

	public void setTekstkomentara(String tekstkomentara) {
		this.tekstkomentara = tekstkomentara;
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