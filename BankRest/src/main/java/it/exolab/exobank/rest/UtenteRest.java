package it.exolab.exobank.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


import it.exolab.bank.local.UtenteControllerLocal;
import it.exolab.bank.models.Ruolo;
import it.exolab.bank.models.Utente;
import it.exolab.exobank.conf.EJBFactory;

@Path("/utenteRest")
public class UtenteRest {
	
	
	private UtenteControllerLocal UtenteControllerLocal;

	public UtenteRest() throws Exception {
	    UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
	}
	
	
	
	@POST
	@Path("/insertUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response insert(Utente utente) {
	    try {
	        // Crea un oggetto Ruolo con ID 2
	        Ruolo ruolo = new Ruolo();
	        ruolo.setId(2);

	        // Imposta il ruolo nell'utente
	        utente.setRuolo(ruolo);

	        // Esegui l'inserimento dell'utente nel database
	        UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
	        utente = UtenteControllerLocal.insert(utente);

	        return Response.status(201).entity(utente).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Response.status(500).build();
	}
	
	@PUT
	@Path("/updateUtente")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response update(Utente utente) {
	    System.out.println("Sei dentro " + this.getClass() + " nel servizio - updateUtente - input -> " + utente);
	    
	    try {
	        UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
	        
	        // Verifica se l'ID specificato è valido
	        Utente existingUser = UtenteControllerLocal.findById(utente.getId());
	        
	        if (existingUser == null) {
	            // L'utente con l'ID specificato non esiste, restituisci una risposta di errore
	            return Response.status(404).entity("L'utente con ID " + utente.getId() + " non esiste.").build();
	        }
	        
	        // Esegui l'aggiornamento
	        UtenteControllerLocal.update(utente);
	        
	        // Restituisci una risposta di successo con l'oggetto utente aggiornato
	        return Response.status(200).entity(utente).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Restituisci una risposta di errore
	        return Response.status(500).entity("Si è verificato un errore durante l'aggiornamento dell'utente.").build();
	    }
	}

	@DELETE
	@Path("/deleteById/{id}")
	@Produces({ "application/json" })
	public Response delete(@PathParam("id") Integer id) {
		
		System.out.println("sei dentro " + this.getClass() + " nel servizio - deleteUtente - input -> " + id);
		try {
			UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
			UtenteControllerLocal.deleteById(id);
			return Response.status(202).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	@GET
	@Path("/getUtente/{idUtente}")
	@Produces({ "application/json" })
	public Response findPathParam(@PathParam("idUtente") Integer idUtente) {

		System.out.println("sei dentro " + this.getClass() + " nel servizio - findPathParam - input -> " + idUtente);
		try {
			UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
			Utente utente = UtenteControllerLocal.findById(idUtente);
			
			if(utente != null) {
				
				
			}
			
			
			
			return  Response.status(200).entity(utente).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  Response.status(500).build();
	}

	/*
	 * utilizzando il queryParam, il parametro viene inserito direttamente nell'url ma con la sintassi del queryString
	 * es: localhost:8080/NomeProgetto/ApplicationPath/ClassPath/MethodPath?idUtente=1 <-- key e value del parametro
	 */
	@GET
	@Path("/getUtente")
	@Produces({ "application/json" })
	public Response findQueryParam(@PathParam("id") Integer id) {

		System.out.println("sei dentro " + this.getClass() + " nel servizio - findQueryParam - input -> " + id);
		try {
			UtenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
			Utente utente = UtenteControllerLocal.findById(id);
			
			if(utente != null) {
				JsonObjectBuilder utenteBuilder = Json.createObjectBuilder()
		                .add("id", utente.getId())
		                .add("nome", utente.getNome())
		                .add("cognome", utente.getCognome())
		                .add("genere", utente.getGenere())
		                .add("dataNascita", utente.getDataNascita()) 
		                .add("email", utente.getEmail())
		                .add("password", utente.getPassword())
		                .add("codRuolo", utente.getRuolo().getId())
		                .add("tipo", utente.getRuolo().getTipo());
				
				JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
		                .add("success", true)
		                .add("message", "Utente trovato")
		                .add("utente", utenteBuilder);
				 JsonObject responseJson = responseBuilder.build();
		            return Response.ok(responseJson).build();
		        } else {
		            return Response.status(404).entity(createErrorResponse(false, "Utente non trovato per l'utente con ID: " + id)).build();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return Response.status(500).entity(createErrorResponse(false, "Errore durante l'esecuzione di findByUtenteId: " + e.getMessage())).build();
		    }
		}
	
	private JsonObject createErrorResponse(boolean success, String message) {
	    JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	        .add("success", success)
	        .add("message", message);

	    return responseBuilder.build();
	}



	@POST
	@Path("/findByEmailPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findAdminByEmailPassword(JsonObject requestObject) {
	    String email = requestObject.getString("email", "");
	    String password = requestObject.getString("password", "");
	    
	    System.out.println("Email ricevuta: " + email);
	    System.out.println("Password ricevuta: " + password);

	    try {
	        UtenteControllerLocal utenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
	        
	        if (utenteControllerLocal != null) {
	            Utente utente = utenteControllerLocal.findByEmailPassword(email, password);

	            if (utente != null) {
	                JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	                    .add("success", true)
	                    .add("message", "Utente trovato")
	                    .add("utente", Json.createObjectBuilder()
	                        .add("id", utente.getId())
	                        .add("email", utente.getEmail())
	                        // Aggiungi tutti gli altri campi dell'utente qui
	                        .add("nome", utente.getNome())
	                        .add("genere", utente.getGenere())
	                        .add("cognome", utente.getCognome())
	                        .add("email", utente.getEmail())
	                        .add("codRuolo", utente.getRuolo().getId())
	                        .add("tipo", utente.getRuolo().getTipo())
	 
	                        
	                        // Aggiungi gli altri campi dell'utente in modo simile
	                    );

	                JsonObject responseJson = responseBuilder.build();

	                return Response.status(200).entity(responseJson).build();
	            } else {
	                JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	                    .add("success", false)
	                    .add("message", "Nessun utente trovato");

	                JsonObject responseJson = responseBuilder.build();

	                return Response.status(404).entity(responseJson).build();
	            }
	        } else {
	            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	                .add("success", false)
	                .add("message", "Errore nel recupero dell'utente");

	            JsonObject responseJson = responseBuilder.build();

	            return Response.status(500).entity(responseJson).build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	            .add("success", false)
	            .add("message", "Errore durante la ricerca dell'utente");

	        JsonObject responseJson = responseBuilder.build();

	        return Response.status(500).entity(responseJson).build();
	    }
	}
	
	@GET
	@Path("/getAllUtenti")
	@Produces({ "application/json" })
	public Response findAllUtenti() {

		 try {
		        UtenteControllerLocal utenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
		        List<Utente> utenti = utenteControllerLocal.findAll();
		        
		        // Costruisci un array JSON di utenti
		        JsonArrayBuilder utentiArrayBuilder = Json.createArrayBuilder();
		        for (Utente utente : utenti) {
		      

		        	
		            JsonObjectBuilder utenteBuilder = Json.createObjectBuilder()
		                .add("id", utente.getId())
		                .add("nome", utente.getNome())
		                .add("cognome", utente.getCognome())
		                .add("genere", utente.getGenere())
		                .add("dataNascita", utente.getDataNascita()) // Converte Timestamp in una stringa
		                .add("email", utente.getEmail())
		                .add("password", utente.getPassword());
		             
		            
		            // Aggiungi le informazioni del ruolo
		            if (utente.getRuolo() != null) {
		            	 utenteBuilder.add("codRuolo", utente.getRuolo().getId());
		                utenteBuilder.add("ruolo", utente.getRuolo().getTipo()); // Supponiamo che ci sia un campo "nome" nel ruolo
		            }

		            utentiArrayBuilder.add(utenteBuilder);
		        }

		        // Costruisci la risposta JSON
		        JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
		            .add("success", true)
		            .add("message", "Utenti trovati")
		            .add("utenti", utentiArrayBuilder);

		        JsonObject responseJson = responseBuilder.build();

		        return Response.status(200).entity(responseJson).build();
		    } catch (Exception e) {
		        e.printStackTrace();
		        JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
		            .add("success", false)
		            .add("message", "Errore durante il recupero degli utenti");

		        JsonObject responseJson = responseBuilder.build();

		        return Response.status(500).entity(responseJson).build();
	}


	
	
	
	
	
//	@POST
//	@Path("/findAdminByEmailPassword")
//	@Produces("application/json")
//	public Response findAdminByEmailPassword(
//	    @QueryParam("email") String email,
//	    @QueryParam("password") String password) {
//	    System.out.println("Email ricevuta: " + email);
//	    System.out.println("Password ricevuta: " + password);
//
//	    try {
//	        UtenteControllerLocal utenteControllerLocal = new EJBFactory<UtenteControllerLocal>(UtenteControllerLocal.class).getEJB();
//	        Utente utente = utenteControllerLocal.findAdminByEmailPassword(email, password);
//
//	        if (utente != null) {
//	            return Response.status(200).entity(utente).build();
//	        } else {
//	            // Nessun utente trovato, restituisci una risposta 404 (Not Found)
//	            return Response.status(404).build();
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return Response.status(500).build();
//	    }
//	}

}}
