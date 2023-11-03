package it.exolab.bank.controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import it.exolab.bank.crud.TransazioneCrud;
import it.exolab.bank.local.TransazioneControllerLocal;
import it.exolab.bank.models.Transazione;

@Stateless (name ="TransazioneControllerLocal") // questo nome serve alla lookUp per ritrovare l'EJB
@LocalBean
public class TransazioneController implements TransazioneControllerLocal{

	@Override
	public List<Transazione> findAll() {
		System.out.println("Dentro TransazioneController    findAll");
		TransazioneCrud crud = new TransazioneCrud();

		return crud.findAll();
	}
	
	@Override
	public List<Transazione> findAllByStato(String stato) {
		System.out.println("findAllByStato");
		TransazioneCrud crud = new TransazioneCrud();
		return crud.findAllByStato(stato);
	}

	@Override
	public List<Transazione> transactionsByUtenteId(Integer idUtente) {
		System.out.println("Dentro TransazioneController    transactionsByUtenteId");
		TransazioneCrud crud = new TransazioneCrud();
		return crud.transactionsByUtenteId(idUtente);
	}
	
	@Override
	public void statoToApprovata(Integer id) {
		System.out.println("statoToApprovata");
		TransazioneCrud c = new TransazioneCrud();
		c.statoToApprovata(id);
	}
	
	@Override
	public void statoToRifiutata(Integer idUtente) {
		System.out.println("statoToApprovata");
		TransazioneCrud c = new TransazioneCrud();
		c.statoToRifiutata(idUtente);
		
	}
	
	
	@Override
	public Transazione findById(Integer idUtente) {
		System.out.println("finById");
		TransazioneCrud c = new TransazioneCrud();
		return c.findById(idUtente);
	}

	@Override
	public void update(Transazione transazione) {
		System.out.println("updateTransanzione");
		TransazioneCrud c = new TransazioneCrud();
		transazione = c.findById(1);
		transazione.setImporto(10);
		c.update(transazione);
		
		
	}

	@Override
	public void insertPrelievo(Transazione transazione) {
		System.out.println("insertPrelievo");
		TransazioneCrud c = new TransazioneCrud();
		c.insertPrelievo(transazione);
	}
}
