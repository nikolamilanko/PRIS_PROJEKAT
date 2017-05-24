package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LOGOVANJE11 database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Logovanje11.findAll", query="SELECT l FROM Logovanje11 l"),
	@NamedQuery(name="Logovanje11.findForUsername", query="SELECT l FROM Logovanje11 l WHERE l.username = :username")
})
@Table(name="LOGOVANJE11")
public class Logovanje11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idlogovanja;

	private String password;

	private String username;

	//bi-directional many-to-one association to Polaznik11
	@OneToMany(mappedBy="logovanje11")
	private List<Polaznik11> polaznik11s;

	//bi-directional many-to-one association to Predavac11
	@OneToMany(mappedBy="logovanje11")
	private List<Predavac11> predavac11s;

	public Logovanje11() {
	}

	public int getIdlogovanja() {
		return this.idlogovanja;
	}

	public void setIdlogovanja(int idlogovanja) {
		this.idlogovanja = idlogovanja;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Polaznik11> getPolaznik11s() {
		return this.polaznik11s;
	}

	public void setPolaznik11s(List<Polaznik11> polaznik11s) {
		this.polaznik11s = polaznik11s;
	}

	public Polaznik11 addPolaznik11(Polaznik11 polaznik11) {
		getPolaznik11s().add(polaznik11);
		polaznik11.setLogovanje11(this);

		return polaznik11;
	}

	public Polaznik11 removePolaznik11(Polaznik11 polaznik11) {
		getPolaznik11s().remove(polaznik11);
		polaznik11.setLogovanje11(null);

		return polaznik11;
	}

	public List<Predavac11> getPredavac11s() {
		return this.predavac11s;
	}

	public void setPredavac11s(List<Predavac11> predavac11s) {
		this.predavac11s = predavac11s;
	}

	public Predavac11 addPredavac11(Predavac11 predavac11) {
		getPredavac11s().add(predavac11);
		predavac11.setLogovanje11(this);

		return predavac11;
	}

	public Predavac11 removePredavac11(Predavac11 predavac11) {
		getPredavac11s().remove(predavac11);
		predavac11.setLogovanje11(null);

		return predavac11;
	}

}