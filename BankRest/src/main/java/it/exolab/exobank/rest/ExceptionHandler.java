package it.exolab.exobank.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        // Qui puoi personalizzare la gestione delle eccezioni, ad esempio registrando gli errori nei log.
        e.printStackTrace(); // Questo stamperà l'errore nella console del server.
        return Response.status(500).entity("Errore interno del server").build();
    }
}
