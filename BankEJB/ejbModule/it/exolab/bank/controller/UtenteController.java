package it.exolab.bank.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import it.exolab.bank.crud.UtenteCrud;
import it.exolab.bank.local.UtenteControllerLocal;
import it.exolab.bank.models.Utente;

/**
 * Session Bean implementation class UtenteController
 */
@Stateless (name ="UtenteControllerLocal") // questo nome serve alla lookUp per ritrovare l'EJB
@LocalBean
public class UtenteController implements UtenteControllerLocal {


	@Override
	public Utente insert(Utente utente) {

		System.out.println("Dentro UtenteController -- insert -- " + utente);
		UtenteCrud utenteCrud = new UtenteCrud();
		utente = utenteCrud.insert(utente);
		return utente;
	}




	@Override
	public void update(Utente utente) {

		System.out.println("Dentro UtenteController -- update -- " + utente);
		UtenteCrud crud = new UtenteCrud();
	
		crud.update(utente);
	
	}


	@Override
	public Utente findById(Integer idUtente) {

		System.out.println("Dentro UtenteController -- findById -- " + idUtente);
		UtenteCrud utenteCrud = new UtenteCrud();
		Utente utente = utenteCrud.findById(idUtente);
		return utente;
	}


	@Override
	public List<Utente> findAll() {

		System.out.println("Dentro UtenteController -- findAll -- ");
		UtenteCrud utenteCrud = new UtenteCrud();

		return utenteCrud.findAll();
	}


	@Override
	public void deleteById(Integer id) {

		System.out.println("Dentro UtenteController -- delete -- " + id);
		UtenteCrud utenteCrud = new UtenteCrud();
		utenteCrud.deleteById(id);;
	}



	@Override
	public Utente findByEmailPassword(String email, String password) {
		UtenteCrud utenteCrud = new UtenteCrud();

		return utenteCrud.findAdminByEmailPassword(email, password);
	}


	public static void main(String[] args) {
		UtenteController c = new UtenteController();
		SimpleDateFormat dateFormatInput = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat dateFormatOutput = new SimpleDateFormat("yyyy-MM-dd");

		Date dataNascita = null;

		try {
		    dataNascita = dateFormatInput.parse("20-10-2395"); // Converti la data nel formato "dd-MM-yy"
		    String formattedDate = dateFormatOutput.format(dataNascita); // Formatta la data nel formato "yyyy-MM-dd"
		    dataNascita = dateFormatOutput.parse(formattedDate); // Converti nuovamente la data in un oggetto Date
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		Utente utente = new Utente("Nome", "Cognome", dataNascita, "email@example.com", "password", "M");
		utente.setId(18);

		
		c.update(utente);
	}









}
