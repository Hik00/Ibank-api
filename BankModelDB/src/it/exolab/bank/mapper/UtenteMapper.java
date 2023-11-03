package it.exolab.bank.mapper;

import java.util.List;

import javax.ws.rs.PathParam;

import org.apache.ibatis.annotations.Param;

import it.exolab.bank.models.Utente;

public interface UtenteMapper {

	void insert(Utente utente);

	void update(Utente utente);

	void deleteById(@PathParam("id") Integer id) ;

	Utente findById(@PathParam("id") Integer id);

	List<Utente> findAll();
	
	Utente findByEmailPassword(@Param("email") String email, @Param("password") String password);
	
	

}
