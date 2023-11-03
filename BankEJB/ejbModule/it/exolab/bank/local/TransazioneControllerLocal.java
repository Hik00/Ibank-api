package it.exolab.bank.local;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.PathParam;

import it.exolab.bank.models.Transazione;

@Local
public interface TransazioneControllerLocal {
	List<Transazione> findAll();
	List<Transazione> transactionsByUtenteId(@PathParam("idUtente") Integer idUtente);
	List<Transazione> findAllByStato(@PathParam("stato") String stato);
	void statoToApprovata(@PathParam("id") Integer id);
	Transazione findById(@PathParam("idUtente") Integer idUtente);
	
	void update(Transazione transazione);
	
    void statoToRifiutata(@PathParam("idUtente") Integer idUtente);
    
	void insertPrelievo(Transazione transazione);


}
