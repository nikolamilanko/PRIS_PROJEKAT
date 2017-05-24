package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import beans.impl.KomunikacijaSaBazomBean;
import entities.Lekcija11;

@ManagedBean
@SessionScoped
public class PregledLekcijeManagedBean {
	private Lekcija11 lekcija11;
	
	@EJB
	private KomunikacijaSaBazomBean komunikacijaSaBazomBean;
	List<File> listOfFiles ;
	int i = 0;
	
	@PostConstruct
	private void testPopuni(){
		lekcija11 = komunikacijaSaBazomBean.getLekcijaForID(3);
		System.out.println("LEKCIJA " + lekcija11.getNazivlekcije());
		File folder = new File("D:/"+lekcija11.getKurs11().getNazivkursa()+"/"+lekcija11.getNazivlekcije());
		listOfFiles = new ArrayList<>();

		for (File file : folder.listFiles()) {
		    if (file.isFile()) {
		        listOfFiles.add(file);
		        System.out.println(file.getName());
		    }
		}
		System.out.println("SIZE LISTE: "+ listOfFiles.size() + "   " + listOfFiles.get(0).getPath());
	}
	
	public StreamedContent streamIzUplFajla() {
		System.out.println("streamIzUplFajla");
		try {
			System.out.println("1");
			Path path = Paths.get(listOfFiles.get(i).getPath());
			System.out.println("2");

			byte[] data = Files.readAllBytes(path);

			StreamedContent ret = new DefaultStreamedContent(new ByteArrayInputStream(data), "image/png");
			System.out.println("3");
			if (i < listOfFiles.size() - 1) {
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
	
	

	public Lekcija11 getLekcija11() {
		return lekcija11;
	}

	public void setLekcija11(Lekcija11 lekcija11) {
		this.lekcija11 = lekcija11;
	}



	public List<File> getListOfFiles() {
		return listOfFiles;
	}



	public void setListOfFiles(List<File> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}
	
	
	
}
