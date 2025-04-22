package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.countdown.CountdownDTO;
import at.fh.joanneum.irfc.service.CountdownService;
import io.quarkus.security.Authenticated;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
@Path("/countdowns")
public class CountdownApi {

    @Inject
    CountdownService countdownService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountdownDTO> getAll() {
        return countdownService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountdownDTO get(@PathParam("id") Long id) {
        return countdownService.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public CountdownDTO create(CountdownDTO countdownDTO) {
        return countdownService.create(countdownDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Authenticated
    public CountdownDTO update(@PathParam("id") Long id, CountdownDTO countdownDTO) {

        return countdownService.update(id, countdownDTO);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        countdownService.delete(id);
        String message = "Countdown with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
