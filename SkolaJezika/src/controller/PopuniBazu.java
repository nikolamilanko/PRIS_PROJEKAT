package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import beans.impl.AutoDodavanjeUNovuBazu;

@ManagedBean
public class PopuniBazu {
	@EJB
	AutoDodavanjeUNovuBazu auto;
	public void popuniBazu(){
		System.out.println("POPUNI BAZU");
		auto.popuniBazu();
	}
}
