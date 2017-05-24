package beans.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Predavac11;

/**
 * Session Bean implementation class PredavacBean
 */
@Stateful
@LocalBean
public class PredavacBean {

	@PersistenceContext
	EntityManager em;
	
	private Predavac11 predavac;

    public PredavacBean() {
        // TODO Auto-generated constructor stub
    }

    public Predavac11 getPredavac() {
		return predavac;
	}

	public void setPredavac(Predavac11 predavac) {
		this.predavac = predavac;
	}
	
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Predavac11> query = em.createNamedQuery(
					"Predavac11.findZaUsernameIPassword",
					Predavac11.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			predavac = query.getResultList().get(0);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
