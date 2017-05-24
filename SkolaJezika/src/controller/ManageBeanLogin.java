package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import beans.impl.KomunikacijaSaBazomBean;
import entities.Kurs11;
import entities.Lekcija11;
import entities.Predavac11;
import entities.Test11;

@ManagedBean(name = "manageBeanLogin")
@ApplicationScoped
public class ManageBeanLogin {

	public static final String ADMINISTRATOR = "administrator";
	public static final String PREDAVAC = "predavac";
	public static final String POLAZNIK = "polaznik";

	private String zanimanje;

	private Date datumPocetka;
	private Date datumKraja;

	private String najboljiKurs;

	private List<Kurs11> kursevi;
	private List<Kurs11> filtriraniKursevi;
	private Kurs11 selektovaniKurs;

	private List<Predavac11> predavaci;
	private List<String> zanimanja;
	
	private List<Test11> nepregledaniTestovi;
	private Test11 selektovaniTest;
	
	private List<Lekcija11> lekcije;
	private Lekcija11 selektovanaLekcija;

	@EJB
	KomunikacijaSaBazomBean bazaBR;

	public void onRowSelect(SelectEvent event) {
		try {
			if (zanimanje.equals(PREDAVAC)) {

				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(
						ec.getRequestContextPath() + "/page-predavac/pregledKursaPredavac.xhtml?faces-redirect=true");

			} else if (zanimanje.equals(ADMINISTRATOR)) {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/page-admin/pregledKursaAdmin.xhtml?faces-redirect=true");
			} else {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(
						ec.getRequestContextPath() + "/page-polaznik/pregledKursaPolaznik.xhtml?faces-redirect=true");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onRowSelectLekcija(SelectEvent event) {
		try {
//			if (zanimanje.equals(PREDAVAC)) {
//
//				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				ec.redirect(
//						ec.getRequestContextPath() + "/page-predavac/pregledLekcije.xhtml?faces-redirect=true");
//
//			} else if (zanimanje.equals(ADMINISTRATOR)) {
//				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				ec.redirect(ec.getRequestContextPath() + "/page-admin/pregledLekcije.xhtml?faces-redirect=true");
//			} else {
//				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				ec.redirect(
//						ec.getRequestContextPath() + "/page-polaznik/pregledLekcije.xhtml?faces-redirect=true");
//			}
			
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/page-svi/pregledLekcije.xhtml?faces-redirect=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onRowSelectTest(SelectEvent event) {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/page-predavac/pregledTesta.xhtml?faces-redirect=true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onLoadZaAdministratora() {
		kursevi = bazaBR.vratiSveKurseve();
		predavaci = bazaBR.vratiSvePredavace();
	}

	public void onLoadZaPredavaca(String username) {
		kursevi = bazaBR.vratiSveKurseveZaUsernamePredavaca(username);
	}

	public void onLoadZaPolaznika(String username) {
		kursevi = bazaBR.vratiSveKurseveZaUsernamePolaznika(username);
	}

	public void vratiSveKurseve() throws IOException {
		kursevi = bazaBR.vratiSveKurseve();
	}

	public void vratiPolozeneKurseve(String username) {
		kursevi = bazaBR.vratiPolozeneKurseveZaPolaznika(username);
	}

	@PostConstruct
	private void init() {
		zanimanja = new ArrayList<>();
		zanimanja.add(ADMINISTRATOR);
		zanimanja.add(PREDAVAC);
		zanimanja.add(POLAZNIK);
		vratiNajboljiKurs();
	}

	private void vratiNajboljiKurs() {
		if (!prviUMesecu()) {
			najboljiKurs = bazaBR.getNajboljiKurs().getNazivkursa();
		} else {
			najboljiKurs = bazaBR.izracunajNajboljiKurs().getNazivkursa();
		}
	}
	
	public void vratiLekcijeITestoveZaKurs() {
		lekcije = bazaBR.getLekcijeForKurs(selektovaniKurs);
		nepregledaniTestovi = bazaBR.getNepregledaniTestoviZaKurs(selektovanaLekcija);
	}

	private boolean prviUMesecu() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1;
	}

	public List<Kurs11> getKursevi() {
		return kursevi;
	}

	public void setKursevi(List<Kurs11> kursevi) {
		this.kursevi = kursevi;
	}

	public List<Kurs11> getFiltriraniKursevi() {
		return filtriraniKursevi;
	}

	public void setFiltriraniKursevi(List<Kurs11> filtriraniKursevi) {
		this.filtriraniKursevi = filtriraniKursevi;
	}

	public List<Predavac11> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(List<Predavac11> predavaci) {
		this.predavaci = predavaci;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}

	public List<String> getZanimanja() {
		return zanimanja;
	}

	public void setZanimanja(List<String> zanimanja) {
		this.zanimanja = zanimanja;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}

	public String getNajboljiKurs() {
		return najboljiKurs;
	}

	public void setNajboljiKurs(String najboljiKurs) {
		this.najboljiKurs = najboljiKurs;
	}

	public Kurs11 getSelektovaniKurs() {
		return selektovaniKurs;
	}

	public void setSelektovaniKurs(Kurs11 selektovaniKurs) {
		this.selektovaniKurs = selektovaniKurs;
	}

	public List<Lekcija11> getLekcije() {
		return lekcije;
	}

	public void setLekcije(List<Lekcija11> lekcije) {
		this.lekcije = lekcije;
	}

	public Lekcija11 getSelektovanaLekcija() {
		return selektovanaLekcija;
	}

	public void setSelektovanaLekcija(Lekcija11 selektovanaLekcija) {
		this.selektovanaLekcija = selektovanaLekcija;
	}

	public List<Test11> getNepregledaniTestovi() {
		return nepregledaniTestovi;
	}

	public void setNepregledaniTestovi(List<Test11> nepregledaniTestovi) {
		this.nepregledaniTestovi = nepregledaniTestovi;
	}

	public Test11 getSelektovaniTest() {
		return selektovaniTest;
	}

	public void setSelektovaniTest(Test11 selektovaniTest) {
		this.selektovaniTest = selektovaniTest;
	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		return "/page-svi/login.xhtml?faces-redirect=true";
	}
}
