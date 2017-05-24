package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import beans.impl.KomunikacijaSaBazomBean;
import entities.Kurs11;
import entities.Lekcija11;
import entities.Slika11;

@ManagedBean
@SessionScoped
public class DodavanjeLekcijeManagedBean {

	@EJB
	KomunikacijaSaBazomBean testBaza;	
	
	boolean isClicked = false;
	private int radioNumberChecked = 0;
	List<File> fajlovi = new ArrayList<>();
	List<Slika11> slike = new ArrayList<>();
	private String opisLekcije = "";
	int i = 0;
	String naziv = "";
	private List<String> tipoviLekcije;
	private String tipLekcije;
	private String videoLink = "";
	private Kurs11 kurs11;

	@PostConstruct
	private void init() {
		tipoviLekcije = new ArrayList<>();
		tipoviLekcije.add("Lekcija sa slikom i tekstom");
		tipoviLekcije.add("Lekcija sa videom");

	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTipLekcije() {
		return tipLekcije;
	}

	public void setTipLekcije(String tipLekcije) {
		this.tipLekcije = tipLekcije;
	}

	public List<String> getTipoviLekcije() {
		return tipoviLekcije;
	}

	public void setTipoviLekcije(List<String> tipoviLekcije) {
		this.tipoviLekcije = tipoviLekcije;
	}

	boolean saSlikom = false;

	boolean saVideom = false;

	public void promeniSaSlikom() {
		saVideom = false;
		saSlikom = true;
	}

	public void promeniSaVideom() {
		saSlikom = false;
		saVideom = true;
	}

	public boolean isSaVideom() {
		return saVideom;
	}

	public void setSaVideom(boolean saVideom) {
		this.saVideom = saVideom;
	}

	public boolean isSaSlikom() {
		return saSlikom;
	}

	public void setSaSlikom(boolean saSlikom) {
		this.saSlikom = saSlikom;
	}

	public DodavanjeLekcijeManagedBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOpisLekcije() {
		return opisLekcije;
	}

	public void setOpisLekcije(String opisLekcije) {
		this.opisLekcije = opisLekcije;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public List<File> getFajlovi() {
		return fajlovi;
	}

	public void setFajlovi(List<File> fajlovi) {
		this.fajlovi = fajlovi;
	}

	public int getRadioNumberChecked() {
		return radioNumberChecked;
	}

	public void setRadioNumberChecked(int radioNumberChecked) {
		this.radioNumberChecked = radioNumberChecked;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Kurs11 getKursTest() {
		return testBaza.findKurs();
	}

	public void upload(FileUploadEvent event) {
		System.out.println("upload");
		System.out.println("OPIS LEKCIJE " + opisLekcije);
		UploadedFile uploadedFile = event.getFile();
		File theDir = new File("D:/"+testBaza.findKurs().getNazivkursa()+"/"+naziv);
		isClicked = true;
		if (!theDir.exists()) {
			theDir.mkdirs();
		}

		File fileToWrite = new File("D:/"+testBaza.findKurs().getNazivkursa()+"/"+naziv+"/" + uploadedFile.getFileName());
		fajlovi.add(fileToWrite);
		Slika11 slika11 = new Slika11();
		slika11.setPutanjaslike(fileToWrite.getPath());
		slike.add(slika11);

		try (FileOutputStream fop = new FileOutputStream(fileToWrite)) {
			if (!fileToWrite.exists()) {
				fileToWrite.createNewFile();
			}
			byte[] contentInBytes = uploadedFile.getContents();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setujuCliked() {
		System.out.println("CLICKED!");
		isClicked = true;
	}

	public StreamedContent streamIzUplFajla() {
		System.out.println("streamIzUplFajla");
		try {
			Path path = Paths.get(fajlovi.get(i).getPath());

			byte[] data = Files.readAllBytes(path);

			StreamedContent ret = new DefaultStreamedContent(new ByteArrayInputStream(data), "image/png");
			if (i < fajlovi.size() - 1) {
				i++;
			} else {
				i = 0;
			}
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	

	public void ispisOpisa() {
		System.out.println(" OPIS!" + opisLekcije);
	}

	public void sacuvajLekicjuZaVideo(Kurs11 kurs11) {
		if ((naziv.equals("")) || (videoLink.equals(""))) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg2').show();");
		} else {
			System.out.println("NAZIV: "+naziv+"     "+videoLink);
			Lekcija11 lekcija11 = new Lekcija11();
			lekcija11.setNazivlekcije(naziv);
			lekcija11.setKurs11(kurs11);
			lekcija11.setTekstlekcije("");
			lekcija11.setVideolekcije(videoLink);
			int idLekcije = testBaza.sacuvajLekcijuUBazu(lekcija11);
			naziv = "";
			videoLink = "";
		}
	}

	public void sacuvajLekciju(Kurs11 kurs11) {
		if ((opisLekcije.equals("")) || (naziv.equals(""))) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg1').show();");
		} else {
			System.out.println("LEKICJA ISPISI" + opisLekcije + "   NAZIV" + naziv);

			Lekcija11 lekcija11 = new Lekcija11();
			lekcija11.setKurs11(kurs11);
			lekcija11.setTekstlekcije(opisLekcije);
			lekcija11.setVideolekcije("");
			lekcija11.setSlika11s(slike);
			lekcija11.setNazivlekcije(naziv);
			int idLekcije = testBaza.sacuvajLekcijuUBazu(lekcija11);
			for (Slika11 slika11 : slike) {
				slika11.setLekcija11(lekcija11);
				testBaza.sacuvajSlikuUBazu(slika11);
			}
			
			naziv = "";
			opisLekcije="";
			isClicked = false;
			fajlovi = new ArrayList<>();
			slike = new ArrayList<>();
			
		}
	}
}
