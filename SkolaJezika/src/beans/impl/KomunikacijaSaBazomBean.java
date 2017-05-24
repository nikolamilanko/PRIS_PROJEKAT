package beans.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
 * Session Bean implementation class TestBazaBean
 */
@Stateless
@LocalBean
public class KomunikacijaSaBazomBean {

	@PersistenceContext
	EntityManager em;

	@Resource
	SessionContext context;

	/**
	 * Default constructor.
	 */
	public KomunikacijaSaBazomBean() {
		// TODO Auto-generated constructor stub
	}

	public Kurs11 findKurs() {
		Kurs11 kurs11 = em.find(Kurs11.class, 1);
		System.out.println(kurs11.getNazivkursa() + " KURS!!!");
		return kurs11;
	}
	public Kurs11 findKursForID(int id) {
		Kurs11 kurs11 = em.find(Kurs11.class, id);
		return kurs11;
	}

	public void sacuvajSlikuUBazu(Slika11 slika) {
		em.persist(slika);
	}

	public Lekcija11 getLekcijaForID(int id) {
		return em.find(Lekcija11.class, id);
	}

	public int sacuvajLekcijuUBazu(Lekcija11 lekcija11) {
		em.persist(lekcija11);
		int idLekcije = lekcija11.getIdlekcije();
		return idLekcije;
	}

	public List<Kurs11> vratiSveKurseve() {
		TypedQuery<Kurs11> q = em.createNamedQuery("Kurs11.findAll", Kurs11.class);
		List<Kurs11> kursevi = q.getResultList();

		if (kursevi != null)
			return kursevi;
		else
			return new ArrayList<>();
	}

	public List<Kurs11> vratiSveKurseveZaUsernamePredavaca(String username) {
		TypedQuery<Kurs11> q = em.createNamedQuery("Kurs11.findAllZaUsernamePredavaca", Kurs11.class);
		q.setParameter("username", username);
		List<Kurs11> kursevi = q.getResultList();

		if (kursevi != null)
			return kursevi;
		else
			return new ArrayList<>();
	}

	public List<Kurs11> vratiSveKurseveZaUsernamePolaznika(String username) {
		TypedQuery<Kurs11> q = em.createNamedQuery("Kurs11.findAllZaUsernamePolaznika", Kurs11.class);
		q.setParameter("username", username);
		List<Kurs11> kursevi = q.getResultList();

		if (kursevi != null)
			return kursevi;
		else
			return new ArrayList<>();
	}
	
	public List<Kurs11> vratiPolozeneKurseveZaPolaznika(String username) {
		TypedQuery<Kurs11> q = em.createNamedQuery("Kurs11.findPolozeneZaUsernamePolaznika", Kurs11.class);
		q.setParameter("username", username);
		List<Kurs11> kursevi = q.getResultList();

		if (kursevi != null)
			return kursevi;
		else
			return new ArrayList<>();
	}

	public List<Predavac11> vratiSvePredavace() {
		TypedQuery<Predavac11> q = em.createNamedQuery("Predavac11.findAll", Predavac11.class);
		List<Predavac11> predavaci = q.getResultList();

		if (predavaci != null)
			return predavaci;
		else
			return new ArrayList<>();
	}

	public boolean izmeniPassword(String username, String noviPassword) {
		try {
			TypedQuery<Logovanje11> q = em.createNamedQuery("Logovanje11.findForUsername", Logovanje11.class);
			q.setParameter("username", username);
			Logovanje11 l = q.getSingleResult();
			l.setPassword(noviPassword);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void savePitanje(Pitanje11 pitanje11) {
		em.persist(pitanje11);
	}

	public void saveOdgovorNaPitanje(Odgovornapitanje11 odgovornapitanje11) {
		em.persist(odgovornapitanje11);
	}

	public Test11 getPrviTest() {

		Test11 res = em.find(Test11.class, 16);
		return res;
	}

	public Test11 getTestForID(int ID) {
		System.out.println("TEST !16!");
		return em.find(Test11.class, ID);
	}

	public void saveTest(Test11 test11) {
		em.persist(test11);
	}

	public List<Pitanje11> getPitanjaForTest(Test11 test11) {
		TypedQuery<Pitanje11> query = em.createQuery("select p from Pitanje11 p where p.test11.idtesta=:idtest",
				Pitanje11.class);
		query.setParameter("idtest", test11.getIdtesta());
		List<Pitanje11> res = query.getResultList();
		return res;
	}

	public ArrayList<Odgovornapitanje11> getOdgovoriNaPitanje(Pitanje11 pitanje11) {
		TypedQuery<Odgovornapitanje11> query = em.createQuery(
				"select o from Odgovornapitanje11 o where o.pitanje11.idpitanja=:idpitanja", Odgovornapitanje11.class);
		query.setParameter("idpitanja", pitanje11.getIdpitanja());
		try {
			ArrayList<Odgovornapitanje11> res = (ArrayList<Odgovornapitanje11>) query.getResultList();
			return res;
		} catch (Exception e) {
			return new ArrayList<Odgovornapitanje11>();
		}

	}

	public void saveOdgovor(Odgovor11 odgovor11) {
		em.persist(odgovor11);
	}

	public int getBrojTacnihOdogovoraZaPitanje(Pitanje11 pitanje11) {
		TypedQuery<Odgovornapitanje11> query = em.createQuery(
				"select o from Odgovornapitanje11 o where o.pitanje11.idpitanja=:idpitanja and o.jetacan=1 ",
				Odgovornapitanje11.class);
		query.setParameter("idpitanja", pitanje11.getIdpitanja());
		try {
			List<Odgovornapitanje11> res = query.getResultList();
			return res.size();
		} catch (Exception e) {
			return 0;
		}
	}

	public ArrayList<Odgovor11> getOdgovorePolaznikaZaPitanje(Pitanje11 pitanje11, Polaznik11 polaznik11) {
		TypedQuery<Odgovor11> query = em.createQuery(
				"select o from Odgovor11 o where o.pitanje11.idpitanja=:idpitanja and o.polaznik11.idpolaznika=:idpolaznika",
				Odgovor11.class);
		query.setParameter("idpitanja", pitanje11.getIdpitanja());
		query.setParameter("idpolaznika", polaznik11.getIdpolaznika());
		try {
			ArrayList<Odgovor11> res = (ArrayList<Odgovor11>) query.getResultList();
			return res;
		} catch (Exception e) {
			try {
				ArrayList<Odgovor11> res = new ArrayList<>();
				res.add(query.getSingleResult());
				return res;
			} catch (Exception ex) {
				// TODO: handle exception
			}

			return new ArrayList<>();
		}
	}

	public void saveRezultat(Rezultat11 rezultat11) {
		em.persist(rezultat11);
	}

	public Rezultat11 getRezultatForPolaznikAndTest(Polaznik11 polaznik11, Test11 test11) {
		TypedQuery<Rezultat11> query = em.createQuery(
				"select r from Rezultat11 r where r.test11.idtesta=:idtesta and r.polaznik11.idpolaznika=:idpolaznika",
				Rezultat11.class);
		query.setParameter("idtesta", test11.getIdtesta());
		query.setParameter("idpolaznika", polaznik11.getIdpolaznika());
		try {
			System.out.println("REZUTAT VREDNOST: " + query.getResultList().get(0).getBrpoena());
			return query.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("REZUTAT VREDNOST: " + query.getSingleResult().getBrpoena());
			return query.getSingleResult();
		}
	}

	public void updateRezultatBrPoena(Rezultat11 rezultat11, Test11 test11, int brPoena) {
		rezultat11.setBrpoena((int) brPoena);
		if (brPoena / test11.getBrpoenatesta() >= 0.5) {
			rezultat11.setPolozio((byte) 1);
		} else {
			rezultat11.setPolozio((byte) 0);
		}
		em.merge(rezultat11);
	}

	public Polaznik11 getPolaznikForID(int id) {
		return em.find(Polaznik11.class, id);
	}
	
	public Kurs11 getNajboljiKurs() {
		TypedQuery<Kurs11> q = em.createNamedQuery("Kurs11.findNajboljiKurs", Kurs11.class);
		
		return q.getSingleResult();
	}

	public Kurs11 izracunajNajboljiKurs() {
		TypedQuery<Object[]> q = em.createNamedQuery("Ocenakursa.findKurseveIOcene", Object[].class);
		List<Object[]> list = q.getResultList();
		
		Kurs11 najboljiKurs = (Kurs11) list.get(0)[0];
		double najboljaOcena = (double) list.get(0)[1];
		for (Object[] o: list) {
			Kurs11 kurs = (Kurs11) o[0];
			double ocena = (double) o[1];
			if (najboljaOcena < ocena) {
				najboljiKurs = kurs;
				najboljaOcena = ocena;
			}
			updateOcenuKursa(kurs, ocena);
		}
		
		return najboljiKurs;
	}

	private void updateOcenuKursa(Kurs11 kurs, double ocena) {
		kurs = em.find(Kurs11.class, kurs.getIdkursa());
		kurs.setProsecnaocenakursa((float) ocena);
	}

	public List<Lekcija11> getLekcijeForKurs(Kurs11 kurs) {
		TypedQuery<Lekcija11> q = em.createNamedQuery("Lekcija11.findAllForKurs", Lekcija11.class);
		q.setParameter("kurs", kurs);
		
		List<Lekcija11> list = q.getResultList();
		if (list != null)
			return list;
		else
			return new ArrayList<>();
	}

	public List<Test11> getNepregledaniTestoviZaKurs(Lekcija11 lekcija) {
		TypedQuery<Test11> q = em.createNamedQuery("Test11.findNepregledaneZaLekciju", Test11.class);
		q.setParameter("lekcija", lekcija);
		
		List<Test11> list = q.getResultList();
		if (list != null)
			return list;
		else
			return new ArrayList<>();
	}
}
