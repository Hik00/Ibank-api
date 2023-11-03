package it.exolab.bank.local;

import java.util.List;

import javax.ejb.Local;

import org.apache.ibatis.annotations.Param;

import it.exolab.bank.models.Ruolo;
import it.exolab.bank.models.Utente;

@Local
public interface RuoloControllerLocal {

	Ruolo insert(Ruolo model);

	Ruolo update(Ruolo model);

	Ruolo findById(Integer idModel);

	List<Ruolo> findAll();

	void delete(Integer id);
	Utente getUtenteByRuoloId(@Param("idRuolo") Integer idRuolo);


}
