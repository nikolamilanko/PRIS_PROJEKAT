package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PREDAVAC11 database table.
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Predavac11.findAll", query = "SELECT p FROM Predavac11 p"),
	@NamedQuery(name = "Predavac11.findZaUsernameIPassword", query = "SELECT p FROM Predavac11 p WHERE p.logovanje11.idlogovanja=(SELECT l.idlogovanja FROM Logovanje11 l WHERE l.username=:username AND l.password=:password)") })
@Table(name = "PREDAVAC11")
public class Predavac11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpredavaca;

	private String emailpredavaca;

	private String imepredavaca;

	private String prezimepredavaca;

	private String titula;

	//bi-directional many-to-one association to Kurs11
	@OneToMany(mappedBy="predavac11")
	private List<Kurs11> kurs11s;

	//bi-directional many-to-one association to Logovanje11
	@ManyToOne
	@JoinColumn(name="IDLOGOVANJA")
	private Logovanje11 logovanje11;

	public Predavac11() {
	}

	public int getIdpredavaca() {
		return this.idpredavaca;
	}

	public void setIdpredavaca(int idpredavaca) {
		this.idpredavaca = idpredavaca;
	}

	public String getEmailpredavaca() {
		return this.emailpredavaca;
	}

	public void setEmailpredavaca(String emailpredavaca) {
		this.emailpredavaca = emailpredavaca;
	}

	public String getImepredavaca() {
		return this.imepredavaca;
	}

	public void setImepredavaca(String imepredavaca) {
		this.imepredavaca = imepredavaca;
	}

	public String getPrezimepredavaca() {
		return this.prezimepredavaca;
	}

	public void setPrezimepredavaca(String prezimepredavaca) {
		this.prezimepredavaca = prezimepredavaca;
	}

	public String getTitula() {
		return this.titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public List<Kurs11> getKurs11s() {
		return this.kurs11s;
	}

	public void setKurs11s(List<Kurs11> kurs11s) {
		this.kurs11s = kurs11s;
	}

	public Kurs11 addKurs11(Kurs11 kurs11) {
		getKurs11s().add(kurs11);
		kurs11.setPredavac11(this);

		return kurs11;
	}

	public Kurs11 removeKurs11(Kurs11 kurs11) {
		getKurs11s().remove(kurs11);
		kurs11.setPredavac11(null);

		return kurs11;
	}

	public Logovanje11 getLogovanje11() {
		return this.logovanje11;
	}

	public void setLogovanje11(Logovanje11 logovanje11) {
		this.logovanje11 = logovanje11;
	}

}