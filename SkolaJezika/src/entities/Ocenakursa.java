package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OCENAKURSA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Ocenakursa.findAll", query="SELECT o FROM Ocenakursa o"),
	@NamedQuery(name="Ocenakursa.findKurseveIOcene", query="SELECT o.kurs11, AVG(o.ocena) FROM Ocenakursa o GROUP BY o.kurs11.idkursa")
})
@Table(name="OCENAKURSA")
public class Ocenakursa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idocene;

	@Temporal(TemporalType.DATE)
	private Date datumocene;

	private int ocena;

	//bi-directional many-to-one association to Kurs11
	@ManyToOne
	@JoinColumn(name="IDKURSA")
	private Kurs11 kurs11;

	//bi-directional many-to-one association to Polaznik11
	@ManyToOne
	@JoinColumn(name="IDPOLAZNIKA")
	private Polaznik11 polaznik11;

	public Ocenakursa() {
	}

	public int getIdocene() {
		return this.idocene;
	}

	public void setIdocene(int idocene) {
		this.idocene = idocene;
	}

	public Date getDatumocene() {
		return this.datumocene;
	}

	public void setDatumocene(Date datumocene) {
		this.datumocene = datumocene;
	}

	public int getOcena() {
		return this.ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
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