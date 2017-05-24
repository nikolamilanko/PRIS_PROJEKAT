package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.primefaces.context.RequestContext;

import beans.AdministratorBeanRemote;

@ManagedBean(name = "dodavanjePredavacaMB")
@ViewScoped
public class DodavanjePredavacaManagedBean {

	@Size(min = 1, max = 30)
	private String ime;

	@Size(min = 1, max = 30)
	private String prezime;

	@Size(min = 2, max = 30)
	private String titula;
	private String email;

	@Size(min = 1, max = 30)
	private String username;

	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@Size(min=1, max=20)
	private String password;

	private String potvrdiPassword;

	@EJB
	AdministratorBeanRemote abr;

	public DodavanjePredavacaManagedBean() {

	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPotvrdiPassword() {
		return potvrdiPassword;
	}

	public void setPotvrdiPassword(String potvrdiPassword) {
		this.potvrdiPassword = potvrdiPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void sacuvajPredavaca() {
		if (abr.dodajPredavaca(ime, prezime, titula, email, username, password)) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgUspesno').show();");
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgNeuspesno').show();");
		}
	}
	
}
