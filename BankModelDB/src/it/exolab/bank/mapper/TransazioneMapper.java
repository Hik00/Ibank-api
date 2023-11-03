package it.exolab.bank.mapper;

import java.util.List;

import javax.ws.rs.PathParam;

import it.exolab.bank.models.Transazione;

public interface TransazioneMapper {

	List<Transazione> findAll();
	List<Transazione> transactionsByUtenteId(@PathParam("idUtente") Integer idUtente);
	List<Transazione> findAllByStato(@PathParam("stato") String stato);
	
	void update(Transazione transazione);
	
    void statoToApprovata(@PathParam("id") Integer id);
    void statoToRifiutata(@PathParam("id") Integer id);
	
	Transazione findById(@PathParam("idUtente") Integer idUtente);
	void insertPrelievo(Transazione transazione);
	

}