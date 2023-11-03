package it.exolab.bank.mapper;

import java.util.List;
import javax.ws.rs.PathParam;
import it.exolab.bank.models.Conto;

public interface ContoMapper {
	Conto findById(@PathParam("id") Integer id);
	
	List<Conto> findAll();
	
	Conto findByUtenteId(@PathParam("id") Integer id);

}
