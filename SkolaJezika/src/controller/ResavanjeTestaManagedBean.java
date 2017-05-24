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
public class ResavanjeTestaManagedBean {

	@EJB
	KomunikacijaSaBazomBean komunikacijaSaBazomBean;

	Test11 test11 = new Test11();
	List<Pitanje11> pitanja = new ArrayList<>();
	Map<Pitanje11, ArrayList<Odgovornapitanje11>> odgovoriZaPitanje = new HashMap<>();
	Map<Pitanje11, ArrayList<Boolean>> polaznikoviOdogovoriNaPitanjaSaViseOpcija = new HashMap<>();
	Map<Pitanje11, String> polaznikoviOdgovoriNaPitanjaBezOpcija = new HashMap<>();
	Polaznik11 polaznik11 = new Polaznik11();

	@PostConstruct
	private void testPopuni() {
		test11 = komunikacijaSaBazomBean.getTestForID(24);
		System.out.println("TEST TEST TEST");
		polaznik11 = komunikacijaSaBazomBean.getPolaznikForID(1);
		pitanja = komunikacijaSaBazomBean.getPitanjaForTest(test11);
		for (Pitanje11 pitanje11 : pitanja) {
			if (komunikacijaSaBazomBean.getOdgovoriNaPitanje(pitanje11).size() > 0) {
				odgovoriZaPitanje.put(pitanje11, komunikacijaSaBazomBean.getOdgovoriNaPitanje(pitanje11));
				polaznikoviOdogovoriNaPitanjaSaViseOpcija.put(pitanje11, new ArrayList<>());

			} else {
				odgovoriZaPitanje.put(pitanje11, new ArrayList<Odgovornapitanje11>());
			}
		}

		for (Entry<Pitanje11, ArrayList<Boolean>> entry : polaznikoviOdogovoriNaPitanjaSaViseOpcija.entrySet()) {
			for (Odgovornapitanje11 odgovornapitanje11 : komunikacijaSaBazomBean.getOdgovoriNaPitanje(entry.getKey())) {
				polaznikoviOdogovoriNaPitanjaSaViseOpcija.get(entry.getKey()).add(false);
			}
		}
	}

	public void promeniBoolUMapi(Pitanje11 pitanje11, Integer index) {
		ArrayList<Boolean> bools = polaznikoviOdogovoriNaPitanjaSaViseOpcija.get(pitanje11);

		if (bools.get(index)) {
			bools.set(index, false);
		} else {
			bools.set(index, true);
		}

		System.out.println("ISPIS ODGOVORA NA PITANJA BEZ OPCIJA");
		for (Entry<Pitanje11, String> entry : polaznikoviOdgovoriNaPitanjaBezOpcija.entrySet()) {
			System.out.println(entry.getKey().getIdpitanja());
		}

	}

	public void sacuvajOdogovre() {
		double brojPoena = 0;
		double brojPoenaPoPitanju = test11.getBrpoenatesta() / pitanja.size();

		for (Entry<Pitanje11, String> entry : polaznikoviOdgovoriNaPitanjaBezOpcija.entrySet()) {
			Odgovor11 odgovor11 = new Odgovor11();
			odgovor11.setTekstodgovora(entry.getValue());
			System.out.println("PITANJE " + entry.getKey().getTekstpitanja());
			odgovor11.setPitanje11(entry.getKey());
			odgovor11.setPolaznik11(polaznik11);
	        odgovor11.setJepregledan("ne");
			komunikacijaSaBazomBean.saveOdgovor(odgovor11);
		}
		for (Entry<Pitanje11, ArrayList<Boolean>> entry : polaznikoviOdogovoriNaPitanjaSaViseOpcija.entrySet()) {
			String obelezeniOdgovori = "";
			int redniBroj = 0;
			for (Boolean bool : entry.getValue()) {
				if (bool) {
					obelezeniOdgovori = obelezeniOdgovori + redniBroj + "#";
				}
				if ((odgovoriZaPitanje.get(entry.getKey()).get(redniBroj).getJetacan() == 1) && bool) {
					brojPoena += brojPoenaPoPitanju
							/ komunikacijaSaBazomBean.getBrojTacnihOdogovoraZaPitanje(entry.getKey());
				} else if ((odgovoriZaPitanje.get(entry.getKey()).get(redniBroj).getJetacan() == 0) && bool) {
					if (brojPoena > 0) {
						brojPoena -= brojPoenaPoPitanju
								/ komunikacijaSaBazomBean.getBrojTacnihOdogovoraZaPitanje(entry.getKey());
					}
				}
				redniBroj++;

			}
			if (obelezeniOdgovori.length() > 0) {
				obelezeniOdgovori = obelezeniOdgovori.substring(0, obelezeniOdgovori.length() - 1);
			}

			Odgovor11 odgovor11 = new Odgovor11();
			odgovor11.setObelezeniiodogovara(obelezeniOdgovori);
			odgovor11.setPitanje11(entry.getKey());
			odgovor11.setPolaznik11(polaznik11);
			odgovor11.setJepregledan("da");
			komunikacijaSaBazomBean.saveOdgovor(odgovor11);

		}
		Rezultat11 rezultat11 = new Rezultat11();
		rezultat11.setBrpoena((int) brojPoena);
		rezultat11.setPolaznik11(polaznik11);
		rezultat11.setTest11(test11);
		if (test11.getBrpoenatesta() / 2 > brojPoena) {
			rezultat11.setPolozio((byte) 0);
		} else {
			rezultat11.setPolozio((byte) 1);
		}
		if (brojPoena / test11.getBrpoenatesta() >= 0.5) {
			rezultat11.setPolozio((byte) 1);
		} else {
			rezultat11.setPolozio((byte) 0);
		}
		komunikacijaSaBazomBean.saveRezultat(rezultat11);
	}

	public Test11 getTest11() {
		return test11;
	}

	public void setTest11(Test11 test11) {
		this.test11 = test11;
	}

	public List<Pitanje11> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<Pitanje11> pitanja) {
		this.pitanja = pitanja;
	}

	public Map<Pitanje11, ArrayList<Odgovornapitanje11>> getOdgovoriZaPitanje() {
		return odgovoriZaPitanje;
	}

	public void setOdgovoriZaPitanje(Map<Pitanje11, ArrayList<Odgovornapitanje11>> odgovoriZaPitanje) {
		this.odgovoriZaPitanje = odgovoriZaPitanje;
	}

	public Map<Pitanje11, ArrayList<Boolean>> getPolaznikoviOdogovoriNaPitanjaSaViseOpcija() {
		return polaznikoviOdogovoriNaPitanjaSaViseOpcija;
	}

	public void setPolaznikoviOdogovoriNaPitanjaSaViseOpcija(
			Map<Pitanje11, ArrayList<Boolean>> polaznikoviOdogovoriNaPitanjaSaViseOpcija) {
		this.polaznikoviOdogovoriNaPitanjaSaViseOpcija = polaznikoviOdogovoriNaPitanjaSaViseOpcija;
	}

	public Map<Pitanje11, String> getPolaznikoviOdgovoriNaPitanjaBezOpcija() {
		return polaznikoviOdgovoriNaPitanjaBezOpcija;
	}

	public void setPolaznikoviOdgovoriNaPitanjaBezOpcija(Map<Pitanje11, String> polaznikoviOdgovoriNaPitanjaBezOpcija) {
		this.polaznikoviOdgovoriNaPitanjaBezOpcija = polaznikoviOdgovoriNaPitanjaBezOpcija;
	}

}
