package it.exolab.bank.local;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.PathParam;

import it.exolab.bank.models.Conto;

@Local
public interface ContoControllerLocal {
	
	Conto findById(@PathParam("id") Integer id);
	
	List<Conto> findAll();
	
	Conto findByUtenteId(@PathParam("id") Integer id);
	

}
