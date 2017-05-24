package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.primefaces.context.RequestContext;

import beans.impl.KomunikacijaSaBazomBean;
import beans.impl.PolaznikBean;

@ManagedBean(name="polaznikMB")
@SessionScoped
public class PolaznikManagedBean {
	private String username;
	private String password;

	private String stariPassword;

	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@Size(min = 1, max = 20)
	private String noviPassword;
	private String potvrdaNovogPassworda;

	@EJB
	PolaznikBean polaznikBR;

	@EJB
	KomunikacijaSaBazomBean bazaBR;

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

	public String getStariPassword() {
		return stariPassword;
	}

	public void setStariPassword(String stariPassword) {
		this.stariPassword = stariPassword;
	}

	public String getNoviPassword() {
		return noviPassword;
	}

	public void setNoviPassword(String noviPassword) {
		this.noviPassword = noviPassword;
	}

	public String getPotvrdaNovogPassworda() {
		return potvrdaNovogPassworda;
	}

	public void setPotvrdaNovogPassworda(String potvrdaNovogPassworda) {
		this.potvrdaNovogPassworda = potvrdaNovogPassworda;
	}

	public PolaznikBean getPolaznikBR() {
		return polaznikBR;
	}

	public void setPolaznikBR(PolaznikBean polaznikBR) {
		this.polaznikBR = polaznikBR;
	}
	
	public void login() {

		if (polaznikBR.login(username, password)) {

			try {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
				ec.redirect(ec.getRequestContextPath() + "/page-polaznik/pocetnaPolaznik.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg1').show();");
			username = "";
			password = "";
		}
	}
	
	public void izmeniPassword() {
		if (stariPassword.equals(password)) {
			if (!bazaBR.izmeniPassword(username, noviPassword)) {
				FacesMessage msg = new FacesMessage("Doslo je do greske prilikom cuvanje novog passworda!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				RequestContext.getCurrentInstance().execute("PF('dlgIzmenaPassworda').hide()");
			}
		} else {
			FacesMessage msg = new FacesMessage("Niste dobro uneli stari password!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
