package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SLIKA11 database table.
 * 
 */
@Entity
@NamedQuery(name="Slika11.findAll", query="SELECT s FROM Slika11 s")
@Table(name="SLIKA11")
public class Slika11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idslike;

	private String putanjaslike;

	//bi-directional many-to-one association to Lekcija11
	@ManyToOne
	@JoinColumn(name="IDLEKCIJE")
	private Lekcija11 lekcija11;

	public Slika11() {
	}

	public int getIdslike() {
		return this.idslike;
	}

	public void setIdslike(int idslike) {
		this.idslike = idslike;
	}

	public String getPutanjaslike() {
		return this.putanjaslike;
	}

	public void setPutanjaslike(String putanjaslike) {
		this.putanjaslike = putanjaslike;
	}

	public Lekcija11 getLekcija11() {
		return this.lekcija11;
	}

	public void setLekcija11(Lekcija11 lekcija11) {
		this.lekcija11 = lekcija11;
	}

}