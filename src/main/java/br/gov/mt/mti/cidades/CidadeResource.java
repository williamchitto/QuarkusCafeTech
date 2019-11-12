package br.gov.mt.mti.cidades;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("cidades")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CidadeResource {
	private static final Logger LOGGER = Logger.getLogger(CidadeResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public Cidade[] get() {
        return entityManager.createNamedQuery("Cidade.findAll", Cidade.class)
              .getResultList().toArray(new Cidade[0]);
    }

    @GET
    @Path("{id}")
    public Cidade getSingle(@PathParam Integer id) {
    	Cidade entity = entityManager.find(Cidade.class, id);
        if (entity == null) {
            throw new WebApplicationException("Cidade with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Cidade cidade) {
        if (cidade.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(cidade);
        return Response.ok(cidade).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Cidade update(@PathParam Integer id, Cidade cidade) {
        if (cidade.getNome() == null) {
            throw new WebApplicationException("Cidade Nome was not set on request.", 422);
        }

        Cidade entity = entityManager.find(Cidade.class, id);

        if (entity == null) {
            throw new WebApplicationException("Cidade with id of " + id + " does not exist.", 404);
        }

        entity.setNome(cidade.getNome());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Integer id) {
    	Cidade entity = entityManager.getReference(Cidade.class, id);
        if (entity == null) {
            throw new WebApplicationException("Cidade with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                    .add("exceptionType", exception.getClass().getName())
                    .add("code", code);

            if (exception.getMessage() != null) {
                    entityBuilder.add("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(entityBuilder.build())
                    .build();
        }

    }
}
