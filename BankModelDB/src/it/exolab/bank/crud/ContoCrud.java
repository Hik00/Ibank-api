package it.exolab.bank.crud;


import java.util.List;

import javax.websocket.server.PathParam;

import it.exolab.bank.mapper.ContoMapper;
import it.exolab.bank.models.Conto;
import it.exolab.bank.mybatis.SqlMapFactory;

public class ContoCrud extends BaseCrud<Conto> {

	@Override
	Conto insert(Conto model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Conto model) {
		// TODO Auto-generated method stub
	}

	@Override
	public Conto findById(@PathParam("id") Integer id) {
//	    SqlSession sqlSession = null;
//	    try {
//	        sqlSession = SqlMapFactory.instance().openSession();
//	        ContoMapper mapper = sqlSession.getMapper(ContoMapper.class);
//	        Conto c = mapper.findById(id);
//	        sqlSession.commit();
//	        return c;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        if (sqlSession != null) {
//	            sqlSession.rollback();
//	        }
//	        // Gestire l'eccezione in modo appropriato, ad esempio registrandola o sollevando una nuova eccezione personalizzata
//	        throw new RuntimeException("Errore durante la ricerca del conto per ID");
//	    } finally {
//	        if (sqlSession != null) {
//	            sqlSession.close();
//	        }
//	    }
		return null;
	}


	@Override
	public List<Conto> findAll() {

		try {
			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			List<Conto> listaConti =mapper.findAll();
			return listaConti;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	
	public Conto findByUtenteId(@PathParam("id") Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			Conto c = mapper.findByUtenteId(id);
			SqlMapFactory.instance().commitSession();
			return c;
			
		} catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
	
	return null;
	}

	
	
	

	
	
	
	public static void main(String[] args) {
		ContoCrud cr = new ContoCrud();
		Conto c = cr.findByUtenteId(1);
		System.out.println(c);
	}

}
