package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LEKCIJA11 database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Lekcija11.findAll", query="SELECT l FROM Lekcija11 l"),
	@NamedQuery(name="Lekcija11.findAllForKurs", query="SELECT l FROM Lekcija11 l where l.kurs11 = :kurs11")
})
@Table(name="LEKCIJA11")
public class Lekcija11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idlekcije;

	private String nazivlekcije;

	@Lob
	private String tekstlekcije;

	private String videolekcije;

	//bi-directional many-to-one association to Kurs11
	@ManyToOne
	@JoinColumn(name="IDKURSA")
	private Kurs11 kurs11;

	//bi-directional many-to-one association to Slika11
	@OneToMany(mappedBy="lekcija11")
	private List<Slika11> slika11s;

	//bi-directional many-to-one association to Test11
	@OneToMany(mappedBy="lekcija11")
	private List<Test11> test11s;

	public Lekcija11() {
	}

	public int getIdlekcije() {
		return this.idlekcije;
	}

	public void setIdlekcije(int idlekcije) {
		this.idlekcije = idlekcije;
	}

	public String getNazivlekcije() {
		return this.nazivlekcije;
	}

	public void setNazivlekcije(String nazivlekcije) {
		this.nazivlekcije = nazivlekcije;
	}

	public String getTekstlekcije() {
		return this.tekstlekcije;
	}

	public void setTekstlekcije(String tekstlekcije) {
		this.tekstlekcije = tekstlekcije;
	}

	public String getVideolekcije() {
		return this.videolekcije;
	}

	public void setVideolekcije(String videolekcije) {
		this.videolekcije = videolekcije;
	}

	public Kurs11 getKurs11() {
		return this.kurs11;
	}

	public void setKurs11(Kurs11 kurs11) {
		this.kurs11 = kurs11;
	}

	public List<Slika11> getSlika11s() {
		return this.slika11s;
	}

	public void setSlika11s(List<Slika11> slika11s) {
		this.slika11s = slika11s;
	}

	public Slika11 addSlika11(Slika11 slika11) {
		getSlika11s().add(slika11);
		slika11.setLekcija11(this);

		return slika11;
	}

	public Slika11 removeSlika11(Slika11 slika11) {
		getSlika11s().remove(slika11);
		slika11.setLekcija11(null);

		return slika11;
	}

	public List<Test11> getTest11s() {
		return this.test11s;
	}

	public void setTest11s(List<Test11> test11s) {
		this.test11s = test11s;
	}

	public Test11 addTest11(Test11 test11) {
		getTest11s().add(test11);
		test11.setLekcija11(this);

		return test11;
	}

	public Test11 removeTest11(Test11 test11) {
		getTest11s().remove(test11);
		test11.setLekcija11(null);

		return test11;
	}

}