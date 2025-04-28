package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.countdown.CountdownDTO;
import at.fh.joanneum.irfc.service.CountdownService;
import io.quarkus.security.Authenticated;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
@Path("/countdown")
public class CountdownApi {

    @Inject
    CountdownService countdownService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public CountdownDTO getCountdown() {
        return countdownService.getCountdown();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public CountdownDTO update(CountdownDTO countdownDTO) {

        return countdownService.update(countdownDTO);
    }
}
