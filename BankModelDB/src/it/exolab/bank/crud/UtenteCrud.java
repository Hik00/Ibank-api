package it.exolab.bank.crud;

import java.util.List;

import javax.ws.rs.PathParam;

import org.apache.ibatis.annotations.Param;

import it.exolab.bank.mapper.UtenteMapper;
import it.exolab.bank.models.Utente;
import it.exolab.bank.mybatis.SqlMapFactory;


public class UtenteCrud extends BaseCrud<Utente> {

	public Utente insert(Utente utente) {

		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			mapper.insert(utente);
			SqlMapFactory.instance().commitSession();
			return utente;
		}catch(Exception e) {	
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public void update(Utente utente) {
	    try {
	        SqlMapFactory.instance().openSession();
	        UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
	        Utente existingUser = mapper.findById(utente.getId());

	        if (existingUser == null) {
	            throw new RuntimeException("L'utente con ID " + utente.getId() + " non esiste.");
	        }

	        mapper.update(utente);
	        System.out.println("Utente modificato");
	        
	        SqlMapFactory.instance().commitSession();

	        // Chiudi la sessione in modo esplicito
	        SqlMapFactory.instance().closeSession();

	    } catch (Exception e) {
	        // Gestisci l'errore in modo più specifico, ad esempio registrando o registrando l'errore.
	        e.printStackTrace();
	        throw new RuntimeException("Si è verificato un errore durante l'aggiornamento dell'utente.", e);
	    }

	}





	public Utente findById (Integer idUtente) {
		System.out.println("nel crud findByIdUtente id---> " + idUtente);
		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			Utente utente = mapper.findById(idUtente);
			System.out.println("utente trovato " + utente);
			return utente;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}




	

	public List <Utente> findAll(){

		try {
			SqlMapFactory.instance().openSession();
			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
			List<Utente> listaUtenti =mapper.findAll();
			return listaUtenti;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	} 
	
	
//	public Utente findAdminByEmailPassword(@Param("email") String email, @Param("password") String password) {
//		try {
//			SqlMapFactory.instance().openSession();
//			UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
//			Utente utente =mapper.findAdminByEmailPassword(email, password);
//			return utente ;
//		}catch(Exception e) {
//			e.printStackTrace();
//			SqlMapFactory.instance().rollbackSession();
//		}finally {
//			SqlMapFactory.instance().closeSession();
//		}
//		return null;
//		
//	}
	
	 public Utente findAdminByEmailPassword(@Param("email") String email, @Param("password") String password) {
	        try {
	            SqlMapFactory.instance().openSession();
	            UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
	            Utente utente = mapper.findByEmailPassword(email, password);
	            return utente;
	        } catch (Exception e) {
	            e.printStackTrace();
	            SqlMapFactory.instance().rollbackSession();
	        } finally {
	            SqlMapFactory.instance().closeSession();
	        }
	        return null;
	    }
	 
	    public void deleteById(@PathParam("id") Integer id) {
	        try {
	            SqlMapFactory.instance().openSession();
	            UtenteMapper mapper = SqlMapFactory.instance().getMapper(UtenteMapper.class);
	            mapper.deleteById(id);
	            SqlMapFactory.instance().commitSession(); // Conferma la transazione solo se l'eliminazione ha successo
	        } catch (Exception e) {
	            e.printStackTrace();
	            SqlMapFactory.instance().rollbackSession(); // Esegui il rollback in caso di errore
	        } finally {
	            SqlMapFactory.instance().closeSession();
	        }
	    }
	 
	 
	 public static void main(String[] args) {
		UtenteCrud c = new UtenteCrud();
		Utente uu = c.findById(1);
		System.out.println(uu);
	 }
	 
	 
	
	

	
}
