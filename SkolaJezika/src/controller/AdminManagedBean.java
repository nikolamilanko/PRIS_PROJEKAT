package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import beans.AdministratorBeanRemote;
import beans.impl.KomunikacijaSaBazomBean;
import entities.Kurs11;
import entities.Predavac11;

@ManagedBean(name = "adminMB")
@SessionScoped
public class AdminManagedBean {
	private String username;
	private String password;

	private String stariPassword;

	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	@Size(min = 1, max = 20)
	private String noviPassword;
	private String potvrdaNovogPassworda;
	
	private Predavac11 selektovaniPredavac;

	private List<Kurs11> kursevi;
	private List<Predavac11> predavaci;

	@EJB
	AdministratorBeanRemote adminBR;

	@EJB
	KomunikacijaSaBazomBean bazaBR;
	
	public List<Predavac11> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(List<Predavac11> predavaci) {
		this.predavaci = predavaci;
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

	public String getStariPassword() {
		return stariPassword;
	}

	public void setStariPassword(String stariPassword) {
		this.stariPassword = stariPassword;
	}

	public AdministratorBeanRemote getAdminBR() {
		return adminBR;
	}

	public void setAdminBR(AdministratorBeanRemote adminBR) {
		this.adminBR = adminBR;
	}

	public List<Kurs11> getKursevi() {
		return kursevi;
	}

	public void setKursevi(List<Kurs11> kursevi) {
		this.kursevi = kursevi;
	}

	public KomunikacijaSaBazomBean getBazaBR() {
		return bazaBR;
	}

	public Predavac11 getSelektovaniPredavac() {
		return selektovaniPredavac;
	}

	public void setSelektovaniPredavac(Predavac11 selektovaniPredavac) {
		this.selektovaniPredavac = selektovaniPredavac;
	}

	public void login() {

		if (adminBR.login(username, password)) {

			try {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
				ec.redirect(ec.getRequestContextPath() + "/page-admin/pocetnaAdmin.xhtml");
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

	public void izmeniPredavaca(RowEditEvent event) {
		if (!adminBR.izmeniPredavaca((Predavac11) event.getObject())) {
			FacesMessage msg = new FacesMessage("Doslo je do greske prilikom izmene predavaca!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void izbrisiPredavaca() {
		if (!adminBR.izbrisiPredavaca(selektovaniPredavac)) {
			FacesMessage msg = new FacesMessage("Doslo je do greske prilikom brisanja predavaca!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
