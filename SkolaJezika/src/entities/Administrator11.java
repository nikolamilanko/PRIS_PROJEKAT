package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADMINISTRATOR11 database table.
 * 
 */
@Entity
@NamedQuery(name="Administrator11.findAll", query="SELECT a FROM Administrator11 a")
@Table(name="ADMINISTRATOR11")
public class Administrator11 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idadmin;

	private String emailadmin;

	private String imeadmin;

	private String prezimeadmin;

	//bi-directional many-to-one association to Logovanje11
	@ManyToOne
	@JoinColumn(name="IDLOGOVANJA")
	private Logovanje11 logovanje11;

	public Administrator11() {
	}

	public int getIdadmin() {
		return this.idadmin;
	}

	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}

	public String getEmailadmin() {
		return this.emailadmin;
	}

	public void setEmailadmin(String emailadmin) {
		this.emailadmin = emailadmin;
	}

	public String getImeadmin() {
		return this.imeadmin;
	}

	public void setImeadmin(String imeadmin) {
		this.imeadmin = imeadmin;
	}

	public String getPrezimeadmin() {
		return this.prezimeadmin;
	}

	public void setPrezimeadmin(String prezimeadmin) {
		this.prezimeadmin = prezimeadmin;
	}

	public Logovanje11 getLogovanje11() {
		return this.logovanje11;
	}

	public void setLogovanje11(Logovanje11 logovanje11) {
		this.logovanje11 = logovanje11;
	}

}