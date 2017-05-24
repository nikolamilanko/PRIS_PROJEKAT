package controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.impl.KomunikacijaSaBazomBean;
import entities.Komentar11;
import entities.Kurs11;
import entities.Polaznik11;

@ManagedBean
@SessionScoped
public class DodavanjeKomentaraManagedBean {
	Kurs11 kurs11;
	Polaznik11 polaznik11;
	String tekstKomentara = "";
	
	
	@EJB
	KomunikacijaSaBazomBean komunikacijaSaBazom;
	
	@PostConstruct
	private void popuniTest(){
		kurs11 = komunikacijaSaBazom.findKursForID(1);
		polaznik11 = komunikacijaSaBazom.getPolaznikForID(1);
	}
	
	
	public void dugmeListener(){
		ispisTeksta();
		sacuvajKomentar();
	}
	
	public void ispisTeksta(){
		System.out.println(tekstKomentara + " TESKST KOMENTARAA");
	}
	public void sacuvajKomentar(){
		Komentar11 komentar11 = new Komentar11();
		komentar11.setDatumkomentara(new Date());
		komentar11.setKurs11(kurs11);
		komentar11.setPolaznik11(polaznik11);
		komentar11.setTekstkomentara(tekstKomentara);
//		komunikacijaSaBazom.sacuvajKomentarUBazu(komentar11);
	}

	public Kurs11 getKurs11() {
		return kurs11;
	}

	public void setKurs11(Kurs11 kurs11) {
		this.kurs11 = kurs11;
	}

	public Polaznik11 getPolaznik11() {
		return polaznik11;
	}

	public void setPolaznik11(Polaznik11 polaznik11) {
		this.polaznik11 = polaznik11;
	}

	public String getTekstKomentara() {
		return tekstKomentara;
	}

	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}
	
	
	
}
