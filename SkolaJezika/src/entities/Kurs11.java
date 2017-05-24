package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the KURS11 database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Kurs11.findAll", query="SELECT k FROM Kurs11 k"),
	@NamedQuery(name="Kurs11.findAllZaUsernamePredavaca", query="SELECT k FROM Kurs11 k WHERE k.predavac11.logovanje11.username = :username"),
	@NamedQuery(name="Kurs11.findAllZaUsernamePolaznika", query="SELECT k FROM Kurs11 k JOIN k.ishod11s i WHERE i.polaznik11.logovanje11.username = :username"),
	@NamedQuery(name="Kurs11.findPolozeneZaUsernamePolaznika", query="SELECT k FROM Kurs11 k JOIN k.ishod11s i WHERE i.polaznik11.logovanje11.username = :username AND i.jepolozio = 1"),
	@NamedQuery(name="Kurs11.findNajboljiKurs", query="SELECT k FROM Kurs11 k WHERE k.prosecnaocenakursa = (SELECT MAX(k1.prosecnaocenakursa) FROM Kurs11 k1)"),
})
@Table(name="KURS11")
public class Kurs11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idkursa;

	@Temporal(TemporalType.DATE)
	private Date datumkraja;

	@Temporal(TemporalType.DATE)
	private Date datumpocetka;

	private String nazivkursa;

	private String opiskursa;

	private int potrebnipoeni;

	private float prosecnaocenakursa;

	//bi-directional many-to-one association to Ishod11
	@OneToMany(mappedBy="kurs11")
	private List<Ishod11> ishod11s;

	//bi-directional many-to-one association to Predavac11
	@ManyToOne
	@JoinColumn(name="IDPREDAVACA")
	private Predavac11 predavac11;

	//bi-directional many-to-one association to Ocenakursa
	@OneToMany(mappedBy="kurs11")
	private List<Ocenakursa> ocenakursas;

	public Kurs11() {
	}

	public int getIdkursa() {
		return this.idkursa;
	}

	public void setIdkursa(int idkursa) {
		this.idkursa = idkursa;
	}

	public Date getDatumkraja() {
		return this.datumkraja;
	}

	public void setDatumkraja(Date datumkraja) {
		this.datumkraja = datumkraja;
	}

	public Date getDatumpocetka() {
		return this.datumpocetka;
	}

	public void setDatumpocetka(Date datumpocetka) {
		this.datumpocetka = datumpocetka;
	}

	public String getNazivkursa() {
		return this.nazivkursa;
	}

	public void setNazivkursa(String nazivkursa) {
		this.nazivkursa = nazivkursa;
	}

	public String getOpiskursa() {
		return this.opiskursa;
	}

	public void setOpiskursa(String opiskursa) {
		this.opiskursa = opiskursa;
	}

	public int getPotrebnipoeni() {
		return this.potrebnipoeni;
	}

	public void setPotrebnipoeni(int potrebnipoeni) {
		this.potrebnipoeni = potrebnipoeni;
	}

	public float getProsecnaocenakursa() {
		return this.prosecnaocenakursa;
	}

	public void setProsecnaocenakursa(float prosecnaocenakursa) {
		this.prosecnaocenakursa = prosecnaocenakursa;
	}

	public List<Ishod11> getIshod11s() {
		return this.ishod11s;
	}

	public void setIshod11s(List<Ishod11> ishod11s) {
		this.ishod11s = ishod11s;
	}

	public Ishod11 addIshod11(Ishod11 ishod11) {
		getIshod11s().add(ishod11);
		ishod11.setKurs11(this);

		return ishod11;
	}

	public Ishod11 removeIshod11(Ishod11 ishod11) {
		getIshod11s().remove(ishod11);
		ishod11.setKurs11(null);

		return ishod11;
	}

	public Predavac11 getPredavac11() {
		return this.predavac11;
	}

	public void setPredavac11(Predavac11 predavac11) {
		this.predavac11 = predavac11;
	}

	public List<Ocenakursa> getOcenakursas() {
		return this.ocenakursas;
	}

	public void setOcenakursas(List<Ocenakursa> ocenakursas) {
		this.ocenakursas = ocenakursas;
	}

	public Ocenakursa addOcenakursa(Ocenakursa ocenakursa) {
		getOcenakursas().add(ocenakursa);
		ocenakursa.setKurs11(this);

		return ocenakursa;
	}

	public Ocenakursa removeOcenakursa(Ocenakursa ocenakursa) {
		getOcenakursas().remove(ocenakursa);
		ocenakursa.setKurs11(null);

		return ocenakursa;
	}

}