package it.exolab.exobank.rest;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import it.exolab.bank.local.ContoControllerLocal;
import it.exolab.bank.models.Conto;
import it.exolab.exobank.conf.EJBFactory;


@Path("/contoRest")
public class ContoRest {

	private ContoControllerLocal contoControllerLocal;

	public ContoRest() throws Exception {
		contoControllerLocal = new EJBFactory<ContoControllerLocal>(ContoControllerLocal.class).getEJB();
	}


	@GET
	@Path("/findAllConti")
	@Produces({ "application/json" })
	public Response findAllConti() {
		try {
			List<Conto> conti = contoControllerLocal.findAll();

			if (conti != null) {
				JsonArrayBuilder contiArrayBuilder = Json.createArrayBuilder();
				for (Conto conto : conti) {
					JsonObjectBuilder utenteBuilder = Json.createObjectBuilder()
							.add("id", conto.getUtente().getId())
							.add("nome", conto.getUtente().getNome())
							.add("cognome", conto.getUtente().getCognome())
							.add("dataNascita", conto.getUtente().getDataNascita())
							.add("genere", conto.getUtente().getGenere())
							.add("email", conto.getUtente().getEmail())
							.add("password", conto.getUtente().getPassword());



					JsonObjectBuilder statoContoBuilder = Json.createObjectBuilder()
							.add("id", conto.getStatoConto().getId())
							.add("esito", conto.getStatoConto().getEsito());

					JsonObjectBuilder contoBuilder = Json.createObjectBuilder()
							.add("id", conto.getId())
							.add("iban", conto.getIban())
							.add("saldo", conto.getSaldo())
							.add("dataApertura", conto.getDataApertura())
							.add("dataEsito", conto.getDataEsito())
							.add("utente", utenteBuilder)
							.add("statoConto", statoContoBuilder);

					contiArrayBuilder.add(contoBuilder);
				}

				JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
						.add("success", true)
						.add("message", "Conti trovati")
						.add("conti", contiArrayBuilder);

				JsonObject responseJson = responseBuilder.build();

				return Response.status(200).entity(responseJson).build();
			} else {
				return createErrorResponse("Errore durante il recupero dei conti", 500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createErrorResponse("Errore durante l'esecuzione di findAllConti: " + e.getMessage(), 500);
		}
	}

	@GET
	@Path("/contoInfoUtente")
	@Produces({ "application/json" })
	public Response contoInfoUtente() {
		try {
			List<Conto> conti = contoControllerLocal.findAll();

			if (conti != null) {
				JsonArrayBuilder contiArrayBuilder = Json.createArrayBuilder();
				for (Conto conto : conti) {
					JsonObjectBuilder utenteBuilder = Json.createObjectBuilder()
							
							.add("nome", conto.getUtente().getNome());
							


					JsonObjectBuilder statoContoBuilder = Json.createObjectBuilder()
							
							.add("esito", conto.getStatoConto().getEsito());

					JsonObjectBuilder contoBuilder = Json.createObjectBuilder()
							.add("id", conto.getId())
							.add("iban", conto.getIban())
							.add("saldo", conto.getSaldo())
							.add("dataApertura", conto.getDataApertura())
							.add("dataEsito", conto.getDataEsito())
							.add("utente", utenteBuilder)
							.add("statoConto", statoContoBuilder);

					contiArrayBuilder.add(contoBuilder);
				}

				JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
						.add("success", true)
						.add("message", "Conti trovati")
						.add("conti", contiArrayBuilder);

				JsonObject responseJson = responseBuilder.build();

				return Response.status(200).entity(responseJson).build();
			} else {
				return createErrorResponse("Errore durante il recupero dei conti", 500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createErrorResponse("Errore durante l'esecuzione di findAllConti: " + e.getMessage(), 500);
		}
	}

	private Response createErrorResponse(String message, int status) {
		JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
				.add("success", false)
				.add("message", message);

		JsonObject responseJson = responseBuilder.build();

		return Response.status(status).entity(responseJson).build();
	}
	
	
	
	@GET
	@Path("/findByUtenteId/{userId}")
	@Produces("application/json")
	public Response findByUtenteId(@PathParam("userId") Integer userId) {
	    try {
	        if (userId == null || userId <= 0) {
	            return Response.status(400).entity(createErrorResponse(false, "UserId non valido")).build();
	        }

	        ContoControllerLocal contoControllerLocal = new EJBFactory<ContoControllerLocal>(ContoControllerLocal.class).getEJB();
	        Conto conto = contoControllerLocal.findByUtenteId(userId);

	        if (conto != null) {
	            JsonObjectBuilder contoBuilder = Json.createObjectBuilder()
	            		.add("nome", conto.getUtente().getNome())
	                .add("id", conto.getId())
	                
	                .add("iban", conto.getIban())
	                .add("saldo", conto.getSaldo())
	                .add("dataApertura", conto.getDataApertura())
	                .add("dataEsito", conto.getDataEsito())
	                .add("codUtente", conto.getUtente().getId())
	                .add("codStatoConto", conto.getStatoConto().getId());
	   
	            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
	                .add("success", true)
	                .add("message", "Transazioni trovate")
	                .add("conto", contoBuilder);

	            JsonObject responseJson = responseBuilder.build();

	            return Response.ok(responseJson).build();
	        } else {
	            return Response.status(404).entity(createErrorResponse(false, "Conto non trovato per l'utente con ID: " + userId)).build();
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

	
	
	
}
