package it.exolab.bank.crud;



import java.util.List;

import javax.websocket.server.PathParam;

import it.exolab.bank.mapper.TransazioneMapper;
import it.exolab.bank.models.Transazione;
import it.exolab.bank.mybatis.SqlMapFactory;

public class TransazioneCrud extends BaseCrud<Transazione>{

	@Override
	Transazione insert(Transazione model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Transazione transazione) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			Transazione existingTransazione = mapper.findById(transazione.getId());

			if (existingTransazione == null) {
				throw new RuntimeException("La transazione con ID " + transazione.getId() + " non esiste.");
			}

			mapper.update(transazione);
			System.out.println("Transazione modificata");

			SqlMapFactory.instance().commitSession();

			// Chiudi la sessione in modo esplicito
			SqlMapFactory.instance().closeSession();

		} catch (Exception e) {
			// Gestisci l'errore in modo più specifico, ad esempio registrando o registrando l'errore.
			e.printStackTrace();
			throw new RuntimeException("Si è verificato un errore durante l'aggiornamento della transazione.", e);
		}

	}

	@Override
	public Transazione findById(@PathParam("id")Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			Transazione t =mapper.findById(id);
			return t;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Transazione> findAll() {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			List<Transazione> listaTransazioni =mapper.findAll();
			return listaTransazioni;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}


	public List<Transazione> findAllByStato(@PathParam("stato") String stato) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			List<Transazione> listaTransazioni =mapper.findAllByStato(stato);
			return listaTransazioni;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public List<Transazione> transactionsByUtenteId(@PathParam("id") Integer id) {

		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			List<Transazione> lista = mapper.transactionsByUtenteId(id);
			SqlMapFactory.instance().commitSession();
			return lista;

		} catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}

		return null;
	}

	public void statoToApprovata(@PathParam("id") Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			Transazione t = mapper.findById(id);
			if (t == null) {
				throw new RuntimeException("Transazione con ID " + id + " non esiste.");
			}



			mapper.statoToApprovata(id);
			System.out.println("Success");

			SqlMapFactory.instance().commitSession();
			SqlMapFactory.instance().closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Si è verificato un errore durante l'aggiornamento della transazione.", e);
		}
	}

	public void statoToRifiutata(@PathParam("id") Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			Transazione t = mapper.findById(id);
			if (t == null) {
				throw new RuntimeException("Transazione con ID " + id + " non esiste.");
			}
			mapper.statoToRifiutata(id);
			System.out.println("Success");

			SqlMapFactory.instance().commitSession();
			SqlMapFactory.instance().closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Si è verificato un errore durante l'aggiornamento della transazione.", e);
		}

	}

	public void insertPrelievo(Transazione transazione) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			mapper.insertPrelievo(transazione);
			SqlMapFactory.instance().commitSession();
		}catch(Exception e) {	
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
	}

}




