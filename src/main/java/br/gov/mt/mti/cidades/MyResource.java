package br.gov.mt.mti.cidades;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Ola Xomanos!!! teste OMG!";
    }
}