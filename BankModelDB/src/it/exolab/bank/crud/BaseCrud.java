package it.exolab.bank.crud;

import java.util.List;

public abstract class BaseCrud<T> {
	
	abstract T insert(T model);
	
	abstract void  update(T model);
	
	//abstract void delete(Integer id);
	
	abstract T findById(Integer id);
	
	
	abstract List<T> findAll();
	
}
