package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import beans.impl.KomunikacijaSaBazomBean;
import beans.impl.PredavacBean;
import entities.Lekcija11;
import entities.Odgovornapitanje11;
import entities.Pitanje11;
import entities.Test11;

@ManagedBean
@SessionScoped
public class TestManagedBean {
	@EJB
	PredavacBean predavacBean;

	@EJB
	KomunikacijaSaBazomBean komunikacijaSaBazomBean;

	Lekcija11 lekcija11;

	Test11 test11 = new Test11();
	// Test11 test11 = komunikacijaSaBazomBean.getPrviTest();

	List<Pitanje11> pitanja = new ArrayList<>();
	Map<Pitanje11, List<Boolean>> mapa = new HashMap<>();
	boolean imaPonudjenihOdgovora = false;

	@PostConstruct
	public void testPopuni() {
		lekcija11 = komunikacijaSaBazomBean.getLekcijaForID(1);

	}

	public void setImaPonudjenihOdgovora() {
		imaPonudjenihOdgovora = true;
	}

	public void dodajOdgovor(Pitanje11 pitanje11) {
		Odgovornapitanje11 odgovornapitanje11 = new Odgovornapitanje11();
		mapa.get(pitanje11).add(false);
		odgovornapitanje11.setJetacan((byte) 0);
		odgovornapitanje11.setTekstodgovora("");
		pitanje11.getOdgovornapitanje11s().add(odgovornapitanje11);
	}

	public void dodajPitanje() {
		komunikacijaSaBazomBean.getPrviTest();

		imaPonudjenihOdgovora = false;
		Pitanje11 pitanjeTemp = new Pitanje11();
		mapa.put(pitanjeTemp, new ArrayList<>());
		pitanjeTemp.setTekstpitanja("");
		pitanjeTemp.setOdgovornapitanje11s(new ArrayList<>());
		pitanja.add(pitanjeTemp);
	}

	public void promeniStanje(Pitanje11 pitanje11, Integer index) {
		List<Boolean> temp = mapa.get(pitanje11);
		System.out.println(temp.get(index) + " POCETAK");
		if (temp.get(index) == true) {
			temp.set(index, false);
		} else {
			temp.set(index, true);
		}
		System.out.println(temp.get(index) + " KRAJ");
	}

	public void savePitanjaUBazu(Lekcija11 lekcija11) {
		if (ispisiGreske()) {
			test11.setLekcija11(lekcija11);
			komunikacijaSaBazomBean.saveTest(test11);
			for (int i = 0; i < pitanja.size(); i++) {
				pitanja.get(i).setTest11(test11);
				for (int j = 0; j < pitanja.get(i).getOdgovornapitanje11s().size(); j++) {
					pitanja.get(i).getOdgovornapitanje11s().get(j)
							.setJetacan(boolToByte((mapa.get(pitanja.get(i)).get(j))));
				}
				komunikacijaSaBazomBean.savePitanje(pitanja.get(i));
				for (int k = 0; k < pitanja.get(i).getOdgovornapitanje11s().size(); k++) {
					pitanja.get(i).getOdgovornapitanje11s().get(k).setPitanje11(pitanja.get(i));
					komunikacijaSaBazomBean.saveOdgovorNaPitanje(pitanja.get(i).getOdgovornapitanje11s().get(k));
				}

			}
		}

	}

	public boolean ispisiGreske() {
		if ((test11.getNaslovtesta().equals("")) || (test11.getBrpoenatesta() < 1)
				|| (test11.getDatumtesta() == null)) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg1').show();");
			return false;
		}
		if ((pitanja.size() < 1)) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg2').show();");
			return false;
		}
		for (Pitanje11 pitanje11 : pitanja) {
			if (pitanje11.getTekstpitanja().equals("")) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dlg3').show();");
				return false;
			}
			if (pitanje11.getOdgovornapitanje11s() != null) {
				for (Odgovornapitanje11 odgovornapitanje11 : pitanje11.getOdgovornapitanje11s()) {
					if (odgovornapitanje11.getTekstodgovora().equals("")) {
						RequestContext context = RequestContext.getCurrentInstance();
						context.execute("PF('dlg3').show();");
						return false;
					}
				}
			}

		}
		boolean check = false;
		for (Map.Entry<Pitanje11, List<Boolean>> entry : mapa.entrySet()) {
			System.out.println("PITANJE: " + entry.getKey().getTekstpitanja() + " POCETAK");
			if ((entry.getValue() != null) && (entry.getValue().size() > 0)) {
				check = false;
				for (Boolean bool : entry.getValue()) {

					if (bool) {
						System.out.println("PITANJE: " + entry.getKey().getTekstpitanja() + "   " + bool);
						check = true;
					}
				}
				if (!check) {
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('dlg4').show();");
					return false;
				}
			}
			check =true;
			System.out.println("PITANJE: " + entry.getKey().getTekstpitanja() + " KRAJ");
		}
		return check;
	}

	public void obrisiPitanje() {
		mapa.remove(pitanja.get(pitanja.size() - 1));
		pitanja.remove(pitanja.size() - 1);

	}

	public byte boolToByte(boolean b) {
		if (b) {
			return 1;
		} else {
			return 0;
		}
	}

	public void obrisiOdogovr(Pitanje11 pitanje11, Integer pozicija) {
		System.out.println(pozicija + "  " + pitanje11.getTekstpitanja());
		System.out.println(pitanje11.getOdgovornapitanje11s().size() + "   " + mapa.get(pitanje11).size());
		pitanje11.getOdgovornapitanje11s().remove((int) pozicija);
		mapa.get(pitanje11).remove((int) pozicija);
		System.out.println(pitanje11.getOdgovornapitanje11s().size() + "   " + mapa.get(pitanje11).size());

	}

	public List<Pitanje11> getPitanja() {
		return pitanja;
	}

	public void setPitanja(List<Pitanje11> pitanja) {
		this.pitanja = pitanja;
	}

	public void sacuvajIzmene() {
		System.out.println(pitanja.get(0).getTekstpitanja());
	}

	public Map<Pitanje11, List<Boolean>> getMapa() {
		return mapa;
	}

	public void setMapa(Map<Pitanje11, List<Boolean>> mapa) {
		this.mapa = mapa;
	}

	public boolean isImaPonudjenihOdgovora() {
		return imaPonudjenihOdgovora;
	}

	public void setImaPonudjenihOdgovora(boolean imaPonudjenihOdgovora) {
		this.imaPonudjenihOdgovora = imaPonudjenihOdgovora;
	}

	public Test11 getTest11() {
		return test11;
	}

	public void setTest11(Test11 test11) {
		this.test11 = test11;
	}

}
