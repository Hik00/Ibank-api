package it.exolab.exobank.conf;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import it.exolab.exobank.rest.ContoRest;
import it.exolab.exobank.rest.ExceptionHandler;
import it.exolab.exobank.rest.TransazioneRest;
import it.exolab.exobank.rest.UtenteRest;


@ApplicationPath("/rest")
public class ControllerRestApplication extends Application {
	
	private Set<Object> singletons;
	//Costruttore della classe di inizializzazione
	public ControllerRestApplication() {
	super();
	CorsFilter corsFilter = new CorsFilter();
	corsFilter.getAllowedOrigins().add("*");
	corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
	singletons = new HashSet<Object>();
	singletons.add(corsFilter);
	}
	@Override
	public Set<Object> getSingletons() {
	return singletons;
	}
	
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<>();
		set.add(UtenteRest.class);
		set.add(ContoRest.class);
		set.add(TransazioneRest.class);
		 set.add(ExceptionHandler.class); // Registra il gestore delle eccezioni globali
		return set;
	}


}
