package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.impl.KomunikacijaSaBazomBean;
import entities.Odgovor11;
import entities.Odgovornapitanje11;
import entities.Pitanje11;
import entities.Polaznik11;
import entities.Rezultat11;
import entities.Test11;

@ManagedBean
@SessionScoped
public class PregledTestaManagedBean {

	@EJB
	KomunikacijaSaBazomBean komunikacijaSaBazomBean;

	Test11 test11 = new Test11();
	Polaznik11 polaznik11 = new Polaznik11();
	List<Pitanje11> pitanja = new ArrayList<>();
	Map<Pitanje11, ArrayList<Odgovor11>> polaznikoviOdgovoriZaPitanje = new HashMap<>();
	Map<Pitanje11, ArrayList<Odgovornapitanje11>> odgovoriZaPitanje = new HashMap<>();
	Map<Pitanje11, Boolean> pregledaniOdgovori = new HashMap<>();
	Map<Pitanje11, ArrayList<Integer>> mapaZaokruzenihOdgovoraZaPitanje = new HashMap<>();

	@PostConstruct
	private void popuniTest() {
		test11 = komunikacijaSaBazomBean.getTestForID(24);
		polaznik11 = komunikacijaSaBazomBean.getPolaznikForID(1);
		pitanja = komunikacijaSaBazomBean.getPitanjaForTest(test11);
		for (Pitanje11 pitanje11 : pitanja) {
			polaznikoviOdgovoriZaPitanje.put(pitanje11,
					komunikacijaSaBazomBean.getOdgovorePolaznikaZaPitanje(pitanje11, polaznik11));
			odgovoriZaPitanje.put(pitanje11, komunikacijaSaBazomBean.getOdgovoriNaPitanje(pitanje11));

		}
		for (Pitanje11 pitanje11 : pitanja) {
			if (odgovoriZaPitanje.get(pitanje11).size() > 0) {
				System.out.println("IF " + odgovoriZaPitanje.get(pitanje11).size());
				mapaZaokruzenihOdgovoraZaPitanje.put(pitanje11, vratiListuBrojevaZaString(pitanje11,
						polaznikoviOdgovoriZaPitanje.get(pitanje11).get(0).getObelezeniiodogovara()));
				for(Integer integer:mapaZaokruzenihOdgovoraZaPitanje.get(pitanje11)){
					System.out.println("INTEGER: "+ integer + " PITANJE: "+ pitanje11.getTekstpitanja());
				}
			}
		}
		
		for(Entry<Pitanje11, ArrayList<Integer>> entry:mapaZaokruzenihOdgovoraZaPitanje.entrySet()){
			for(Integer integer : entry.getValue()){
				System.out.println("INTEGER: "+ integer);
			}
		}
	}
	
	public void dodajRezultat(){
		Rezultat11 rezultat11 = komunikacijaSaBazomBean.getRezultatForPolaznikAndTest(polaznik11, test11);
		System.out.println("ID REZULTATA: " + rezultat11.getIdrezultata());
		double brojPoena = rezultat11.getBrpoena();
		double brojPoenaPoPitanju = test11.getBrpoenatesta()/pitanja.size();
		for(Entry<Pitanje11, Boolean> entry : pregledaniOdgovori.entrySet()){
			if(entry.getValue()){
				brojPoena+=brojPoenaPoPitanju;
			}
		}
		komunikacijaSaBazomBean.updateRezultatBrPoena(rezultat11, test11, (int) brojPoena);
	}

	public ArrayList<Integer> vratiListuBrojevaZaString(Pitanje11 pitanje11, String str) {
		System.out.println("USAO" + str);
		String[] res = str.split("#");
		ArrayList<Integer> forReturn = new ArrayList<>();
		
		for(int i=0;i<odgovoriZaPitanje.get(pitanje11).size();i++){
			boolean dodat = false;
			for(String string : res){
				if(Integer.parseInt(string)==i){
					forReturn.add(i);
					dodat = true;
				}
			}
			if(!dodat){
				forReturn.add(-1);
			}
		}
		System.out.println("IZASAO");
		return forReturn;
	}

	public void dodajUPregledaneOdgovore(Pitanje11 pitanje11) {
		System.out.println(pregledaniOdgovori.get(pitanje11));
		try {
			if (pregledaniOdgovori.get(pitanje11)) {
				pregledaniOdgovori.put(pitanje11, false);
			} else {
				pregledaniOdgovori.put(pitanje11, true);
			}
		} catch (Exception e) {
			pregledaniOdgovori.put(pitanje11, true);
		}
		System.out.println(pregledaniOdgovori.get(pitanje11));
	}

	public Test11 getTest11() {
		return test11;
	}

	public void setTest11(Test11 test11) {
		this.test11 = test11;
	}

	public Polaznik11 getPolaznik11() {
		return polaznik11;
	}

	public void setPolaznik11(Polaznik11 polaznik11) {
		this.polaznik11 = polaznik11;
	}

	public List<Pitanje11> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<Pitanje11> pitanja) {
		this.pitanja = pitanja;
	}

	public Map<Pitanje11, ArrayList<Odgovor11>> getPolaznikoviOdgovoriZaPitanje() {
		return polaznikoviOdgovoriZaPitanje;
	}

	public void setPolaznikoviOdgovoriZaPitanje(Map<Pitanje11, ArrayList<Odgovor11>> polaznikoviOdgovoriZaPitanje) {
		this.polaznikoviOdgovoriZaPitanje = polaznikoviOdgovoriZaPitanje;
	}

	public Map<Pitanje11, ArrayList<Odgovornapitanje11>> getOdgovoriZaPitanje() {
		return odgovoriZaPitanje;
	}

	public void setOdgovoriZaPitanje(Map<Pitanje11, ArrayList<Odgovornapitanje11>> odgovoriZaPitanje) {
		this.odgovoriZaPitanje = odgovoriZaPitanje;
	}

	public Map<Pitanje11, Boolean> getPregledaniOdgovori() {
		return pregledaniOdgovori;
	}

	public void setPregledaniOdgovori(Map<Pitanje11, Boolean> pregledaniOdgovori) {
		this.pregledaniOdgovori = pregledaniOdgovori;
	}

	public Map<Pitanje11, ArrayList<Integer>> getMapaZaokruzenihOdgovoraZaPitanje() {
		return mapaZaokruzenihOdgovoraZaPitanje;
	}

	public void setMapaZaokruzenihOdgovoraZaPitanje(
			Map<Pitanje11, ArrayList<Integer>> mapaZaokruzenihOdgovoraZaPitanje) {
		this.mapaZaokruzenihOdgovoraZaPitanje = mapaZaokruzenihOdgovoraZaPitanje;
	}

}
