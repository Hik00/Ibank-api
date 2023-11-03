package it.exolab.bank.local;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.PathParam;

import org.apache.ibatis.annotations.Param;

import it.exolab.bank.models.Utente;

@Local
public interface UtenteControllerLocal {

	Utente insert(Utente model);

	void update(Utente model);

	Utente findById(@PathParam("id") Integer id);

	List<Utente> findAll();

	void deleteById(@PathParam("id") Integer id) ;
	
	Utente findByEmailPassword(@Param("email") String email, @Param("password") String password);
	


}
