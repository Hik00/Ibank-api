package it.exolab.bank.controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import it.exolab.bank.crud.ContoCrud;
import it.exolab.bank.local.ContoControllerLocal;

import it.exolab.bank.models.Conto;


/**
 * Session Bean implementation class UtenteController
 */
@Stateless (name ="ContoControllerLocal") // questo nome serve alla lookUp per ritrovare l'EJB
@LocalBean
public class ContoController implements ContoControllerLocal {

	@Override
	public Conto findById(Integer id) {
		System.out.println("Dentro UtenteController -- findById -- ");
		ContoCrud crud = new ContoCrud();
	
		return crud.findById(id);
	}

	@Override
	public List<Conto> findAll() {
		System.out.println("Dentro UtenteController -- findAll -- ");
		ContoCrud crud = new ContoCrud();
		
	
		return crud.findAll();
	}

	@Override
	public Conto findByUtenteId(Integer id) {
		System.out.println("Dentro UtenteController -- findByUtenteId");
		ContoCrud crud = new ContoCrud();
		return crud.findByUtenteId(id);
	}

	public static void main(String[] args) {
		ContoController cr = new ContoController();
		Conto c = cr.findByUtenteId(4);
		System.out.println(c);
	}

}
