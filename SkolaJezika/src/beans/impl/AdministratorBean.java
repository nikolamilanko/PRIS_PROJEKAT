package beans.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import beans.AdministratorBeanRemote;
import entities.Administrator11;
import entities.Logovanje11;
import entities.Predavac11;

/**
 * Session Bean implementation class AdministratorBean
 */
@Stateful
@LocalBean
public class AdministratorBean implements AdministratorBeanRemote {

	private Administrator11 administrator;

	@PersistenceContext
	EntityManager em;

	public AdministratorBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Administrator11> query = em.createQuery(
					"select a from Administrator11 a where a.logovanje11.idlogovanja=(select l.idlogovanja from Logovanje11 l where l.username=:username and l.password=:password)",
					Administrator11.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			administrator = query.getResultList().get(0);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Administrator11 getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator11 administrator) {
		this.administrator = administrator;
	}

	@Override
	public boolean dodajPredavaca(String ime, String prezime, String titula, String email, String username,
			String password) {
		// TODO Auto-generated method stub
		Predavac11 p = new Predavac11();
		p.setImepredavaca(ime);
		p.setPrezimepredavaca(prezime);
		p.setEmailpredavaca(email);
		p.setTitula(titula);
		
		Logovanje11 log;
		if ((log = dodajLogovanje(username, password)) != null)
			p.setLogovanje11(log);
		else
			return false;
		
		em.persist(p);

		return true;
	}

	private Logovanje11 dodajLogovanje(String username, String password) {
		try {
			if (postojiUsername(username))
				return null;
			else {
				Logovanje11 log = new Logovanje11();
				log.setUsername(username);
				log.setPassword(password);
				em.persist(log);
				return log;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	private boolean postojiUsername(String username) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Logovanje11> q = em.createNamedQuery("Logovanje11.findForUsername", Logovanje11.class);
			q.setParameter("username", username);
			q.getSingleResult();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean izmeniPredavaca(Predavac11 predavac) {
		// TODO Auto-generated method stub
		try {
			Predavac11 p = (Predavac11) em.find(Predavac11.class, predavac.getIdpredavaca());
			this.dodeliVrednostiPredavacu(p, predavac);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private void dodeliVrednostiPredavacu(Predavac11 p1, Predavac11 p2) {
		p1.setImepredavaca(p2.getImepredavaca());
		p1.setPrezimepredavaca(p2.getPrezimepredavaca());
		p1.setEmailpredavaca(p2.getEmailpredavaca());
		p1.setTitula(p2.getTitula());
	}

	@Override
	public boolean izbrisiPredavaca(Predavac11 predavac) {
		// TODO Auto-generated method stub
		try {
			Predavac11 p = (Predavac11) em.find(Predavac11.class, predavac.getIdpredavaca());
			Logovanje11 l = (Logovanje11) em.find(Logovanje11.class, p.getLogovanje11().getIdlogovanja());
			em.remove(p);
			em.remove(l);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
