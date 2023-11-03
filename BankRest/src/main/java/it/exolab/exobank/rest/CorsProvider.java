package it.exolab.exobank.rest;


import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

@Provider
public class CorsProvider extends Application {

    private Set<Object> singletons = new HashSet<>();

    private CorsProvider() {
    }

    private static class CorsProviderHolder {
        private static final CorsProvider INSTANCE = new CorsProvider();
    }

    public static CorsProvider getInstance() {
        return CorsProviderHolder.INSTANCE;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
