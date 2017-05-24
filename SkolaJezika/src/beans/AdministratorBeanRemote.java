package beans;

import javax.ejb.Remote;

import entities.Administrator11;
import entities.Predavac11;

@Remote
public interface AdministratorBeanRemote {

	boolean login(String username, String password);
	
	boolean dodajPredavaca(String ime, String prezime, String titula, String email, String username, String password);
	
	boolean izmeniPredavaca(Predavac11 predavac);
	
	boolean izbrisiPredavaca(Predavac11 predavac);

	public Administrator11 getAdministrator();
}
