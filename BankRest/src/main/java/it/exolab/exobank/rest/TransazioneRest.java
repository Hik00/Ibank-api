package it.exolab.exobank.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;



import it.exolab.bank.local.TransazioneControllerLocal;
import it.exolab.bank.local.UtenteControllerLocal;
import it.exolab.bank.models.Ruolo;
import it.exolab.bank.models.Transazione;
import it.exolab.bank.models.Utente;
import it.exolab.exobank.conf.EJBFactory;

@Path("/transazioneRest")
public class TransazioneRest {
	private TransazioneControllerLocal transazioneControllerLocal;

	public TransazioneRest() throws Exception {
		transazioneControllerLocal = new EJBFactory<TransazioneControllerLocal>(TransazioneControllerLocal.class).getEJB();
	}

	@GET
	@Path("/byUtenteId/{utenteId}")
	@Produces("application/json")
	public Response getTransazioniByUtenteId(@PathParam("utenteId") Integer utenteId) {
	    try {
	        List<Transazione> transazioni = transazioneControllerLocal.transactionsByUtenteId(utenteId);

	        if (transazioni != null) {
	            JsonArrayBuilder transazioniArrayBuilder = Json.createArrayBuilder();

	            for (Transazione t : transazioni) {
	                JsonObjectBuilder transazioneBuilder = Json.createObjectBuilder()
	                        .add("id", t.getId())
	                        .add("importo", t.getImporto());

	                if (t.getConto() != null) {
	                    transazioneBuilder.add("iban", t.getConto().getIban());
	                } else {
	                    transazioneBuilder.add("iban", "Iban non disponibile");
	                }

	                if (t.getTipoTransazione() != null) {
	                    transazioneBuilder.add("tipoTransazione", t.getTipoTransazione().getTipo());
	                } else {
	                    transazioneBuilder.add("tipoTransazione", "Tipo di transazione non disponibile");
	                }

	                if (t.getDataEsito() != null) {
	                    transazioneBuilder.add("dataEsito", t.getDataEsito());
	                } else {
	                    transazioneBuilder.add("dataEsito", "Data non disponibile");
	                }

	                transazioniArrayBuilder.add(transazioneBuilder);
	            }

	            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	                    .add("success", true)
	                    .add("message", "Transazioni trovate")
	                    .add("transazioni", transazioniArrayBuilder);

	            JsonObject responseJson = responseBuilder.build();

	            return Response.status(200).entity(responseJson).build();
	        } else {
	            throw new RuntimeException("Nessuna transazione trovata per l'utente con ID " + utenteId);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Stampa l'eccezione per scopi di debugging
	        return Response.status(500).entity("Errore durante l'esecuzione di byUtenteId: " + e.getMessage()).build();
	    }
	}
	
	
	


	@GET
	@Path("/findAll")
	@Produces({ "application/json" })
	public Response findAllUtenti( ) {

		try {
			List<Transazione> list = transazioneControllerLocal.findAll();

			if (list != null) {
				// Costruisci un array JSON di utenti
				JsonArrayBuilder transazioniArrayBuilder = Json.createArrayBuilder();
				for (Transazione t : list) {
					JsonObjectBuilder transazioneBuilder = Json.createObjectBuilder()
							.add("id", t.getId())
							.add("importo", t.getImporto())
							.add("data", t.getData())
							.add("codUtente", t.getUtente().getId())
							.add("codConto", t.getConto().getId())
							.add("tipo", t.getTipoTransazione().getTipo())
							.add("stato", t.getStatoTransazione().getEsito());
					
					if (t.getDataEsito() != null) {
					    transazioneBuilder.add("dataEsito", t.getDataEsito());
					} else {
					    transazioneBuilder.add("dataEsito", "N/D");
					}
					
					
					if (t.getInfBonifico() != null) {
					    transazioneBuilder.add("codBonifico", t.getInfBonifico().getId());
					} else {
					    transazioneBuilder.add("codBonifico", "N/D");
					}


					transazioniArrayBuilder.add(transazioneBuilder);

				}
				JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
						.add("success", true)
						.add("message", "Transazioni trovati")
						.add("transazioni", transazioniArrayBuilder);

				JsonObject responseJson = responseBuilder.build();

				return Response.status(200).entity(responseJson).build();
			} else {
				throw new RuntimeException("Errore durante il recupero delle transazioni");
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore durante l'esecuzione di findAll", e);
		}
	}
	
	
	@GET
	@Path("/findByStato/{stato}")
	@Produces({ "application/json" })
	public Response findAllByStato( @PathParam("stato") String stato) {

		try {
			List<Transazione> list = transazioneControllerLocal.findAllByStato(stato);

			if (list != null) {
				// Costruisci un array JSON di utenti
				JsonArrayBuilder transazioniArrayBuilder = Json.createArrayBuilder();
				for (Transazione t : list) {
					JsonObjectBuilder transazioneBuilder = Json.createObjectBuilder()
							.add("id", t.getId())
							.add("importo", t.getImporto())
							.add("data", t.getData())
							.add("codUtente", t.getUtente().getId())
							.add("codConto", t.getConto().getId())
							.add("tipo", t.getTipoTransazione().getTipo())
							.add("stato", t.getStatoTransazione().getEsito());
					
					if (t.getDataEsito() != null) {
					    transazioneBuilder.add("dataEsito", t.getDataEsito());
					} else {
					    transazioneBuilder.add("dataEsito", "N/D");
					}
					
					
					if (t.getInfBonifico() != null) {
					    transazioneBuilder.add("codBonifico", t.getInfBonifico().getId());
					} else {
					    transazioneBuilder.add("codBonifico", "N/D");
					}


					transazioniArrayBuilder.add(transazioneBuilder);

				}
				JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
						.add("success", true)
						.add("message", "Transazioni trovati")
						.add("transazioni", transazioniArrayBuilder);

				JsonObject responseJson = responseBuilder.build();

				return Response.status(200).entity(responseJson).build();
			} else {
				throw new RuntimeException("Errore durante il recupero delle transazioni");
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore durante l'esecuzione di findAll", e);
		}
	}
	
	
	@PUT
	@Path("/statoToApprovata/{id}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response update(@PathParam("id") Integer id) {
	    System.out.println("Sei dentro " + this.getClass() + " nel servizio - updateUtente - input -> " + id);
	    
	    try {
	        transazioneControllerLocal = new EJBFactory<TransazioneControllerLocal>(TransazioneControllerLocal.class).getEJB();
	        
	        
	
	        transazioneControllerLocal.statoToApprovata(id);
	        
	        // Restituisci una risposta di successo con l'oggetto utente aggiornato
	        return Response.status(200).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Restituisci una risposta di errore
	        return Response.status(500).entity("Si è verificato un errore durante l'aggiornamento della transazioen.").build();
	    }
	}
	
	@PUT
	@Path("/statoToRifiutata/{id}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response statoToRifiutata(@PathParam("id") Integer id) {
	    System.out.println("Sei dentro " + this.getClass() + " nel servizio - statoToRifiutata - input -> " + id);
	    
	    try {
	        transazioneControllerLocal = new EJBFactory<TransazioneControllerLocal>(TransazioneControllerLocal.class).getEJB();
	        transazioneControllerLocal.statoToRifiutata(id);
	        
	        // Restituisci una risposta di successo con l'oggetto utente aggiornato
	        return Response.status(200).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Restituisci una risposta di errore
	        return Response.status(500).entity("Si è verificato un errore durante l'aggiornamento della transazione.").build();
	    }
	}
	
	@POST
	@Path("/insertPrelievo")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response insertPrelievo(Transazione transazione) {
	    try {
	        transazioneControllerLocal = new EJBFactory<TransazioneControllerLocal>(TransazioneControllerLocal.class).getEJB();
	        transazioneControllerLocal.insertPrelievo(transazione);

	        return Response.status(200).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Response.status(500).build();
	}
}





