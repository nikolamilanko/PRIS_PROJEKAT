package beans.impl;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Administrator11;
import entities.Ishod11;
import entities.Komentar11;
import entities.Kurs11;
import entities.Lekcija11;
import entities.Logovanje11;
import entities.Odgovor11;
import entities.Odgovornapitanje11;
import entities.Pitanje11;
import entities.Polaznik11;
import entities.Predavac11;
import entities.Rezultat11;
import entities.Slika11;
import entities.Test11;

/**
 * Session Bean implementation class AutoDodavanjeUNovuBazu
 */
@LocalBean
@Stateless
public class AutoDodavanjeUNovuBazu {
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public AutoDodavanjeUNovuBazu() {

	}

	public void popuniBazu() {
		// dodavanje logovanja koje ce biti vezano za admina
		System.out.println(1);
		Logovanje11 logovanjeAdmin = new Logovanje11();
		logovanjeAdmin.setUsername("admin");
		logovanjeAdmin.setPassword("admin");
		em.persist(logovanjeAdmin);
		// dodavanje logovanja koje ce biti vezano za predavaca
		System.out.println(2);
		Logovanje11 logovanjePredavac = new Logovanje11();
		logovanjePredavac.setUsername("predavac");
		logovanjePredavac.setPassword("predavac");
		em.persist(logovanjePredavac);
		// dodavanje logovanja koje ce biti vezano za polaznika
		System.out.println(3);
		Logovanje11 logovanjePolaznik = new Logovanje11();
		logovanjePolaznik.setUsername("polaznik");
		logovanjePolaznik.setPassword("polaznik");
		em.persist(logovanjePolaznik);
		// dodavanje predavaca
		System.out.println(4);
		Predavac11 predavac11 = new Predavac11();
		predavac11.setImepredavaca("predavac");
		predavac11.setPrezimepredavaca("predavac");
		predavac11.setEmailpredavaca("predavac@predavac.com");
		predavac11.setTitula("predavac");
		predavac11.setLogovanje11(logovanjePredavac);
		em.persist(predavac11);
		// dodavanje admina
		System.out.println(5);
		Administrator11 administrator11 = new Administrator11();
		administrator11.setImeadmin("admin");
		administrator11.setPrezimeadmin("admin");
		administrator11.setEmailadmin("admin@admin.admin");
		administrator11.setLogovanje11(logovanjeAdmin);
		em.persist(administrator11);
		// dodavanje polaznika
		System.out.println(6);
		Polaznik11 polaznik11 = new Polaznik11();
		polaznik11.setImepolaznika("polaznik");
		polaznik11.setPrezimepolaznika("polaznik");
		polaznik11.setEmailpolaznika("polaznik@polaznik.polaznik");
		polaznik11.setDatumrodjenja(new Date());
		polaznik11.setLogovanje11(logovanjePolaznik);
		em.persist(polaznik11);
		// dodavanje kursa
		System.out.println(7);
		Kurs11 kurs11 = new Kurs11();
		kurs11.setDatumkraja(new Date());
		kurs11.setDatumpocetka(new Date());
		kurs11.setNazivkursa("kurs");
		kurs11.setOpiskursa("opis");
		kurs11.setPotrebnipoeni(100);
		kurs11.setPredavac11(predavac11);
		em.persist(kurs11);
		// dodavanje ishoda
		Ishod11 ishod11 = new Ishod11();
		byte jePolozio = 1;
		ishod11.setJepolozio(jePolozio);
		ishod11.setKurs11(kurs11);
		ishod11.setPolaznik11(polaznik11);
		em.persist(ishod11);
		// dodavanje komentara
		System.out.println(8);
		Komentar11 komentar11 = new Komentar11();
		komentar11.setPolaznik11(polaznik11);
		komentar11.setKurs11(kurs11);
		komentar11.setTekstkomentara("tekst");
		komentar11.setDatumkomentara(new Date());
		em.persist(komentar11);
		// dodavanje lekcija
		System.out.println(9);
		Lekcija11 lekcija11 = new Lekcija11();
		lekcija11.setTekstlekcije("tekst");
		lekcija11.setVideolekcije("video");
		lekcija11.setNazivlekcije("naziv");
		lekcija11.setKurs11(kurs11);
		em.persist(lekcija11);
		// dodavanje slika
		System.out.println(10);
		Slika11 slika11 = new Slika11();
		slika11.setLekcija11(lekcija11);
		slika11.setPutanjaslike("putanja");
		em.persist(slika11);
		// dodavanje testa
		System.out.println(11);
		Test11 test11 = new Test11();
		test11.setDatumtesta(new Date());
		test11.setBrpoenatesta(50);
		test11.setNaslovtesta("naslov");
		test11.setLekcija11(lekcija11);
		em.persist(test11);
		// dodavanje pitanja
		System.out.println(12);
		Pitanje11 pitanje11 = new Pitanje11();
		pitanje11.setTest11(test11);
		pitanje11.setTekstpitanja("tekst");
		em.persist(pitanje11);
		//dodavanje odgovora na pitanje
		Odgovornapitanje11 odgovornapitanj11 = new Odgovornapitanje11();
		odgovornapitanj11.setJetacan(jePolozio);
		odgovornapitanj11.setPitanje11(pitanje11);
		odgovornapitanj11.setTekstodgovora("tekst odgovora");
		em.persist(odgovornapitanj11);
		// dodavanje odgovora
		System.out.println(13);
		Odgovor11 odgovor11 = new Odgovor11();
		odgovor11.setPolaznik11(polaznik11);
		odgovor11.setPitanje11(pitanje11);
		odgovor11.setTekstodgovora("tekst");
		odgovor11.setObelezeniiodogovara("1,2");
		em.persist(odgovor11);

		// dodavanje rezultata
		Rezultat11 rezultat11 = new Rezultat11();
		rezultat11.setBrpoena(50);
		rezultat11.setPolaznik11(polaznik11);
		rezultat11.setPolozio(jePolozio);
		rezultat11.setTest11(test11);
		em.persist(rezultat11);
	}
}
