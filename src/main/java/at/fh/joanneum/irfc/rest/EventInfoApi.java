package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.service.EventInfoService;
import io.quarkus.security.Authenticated;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
@Path("/eventInfos")
public class EventInfoApi {

    @Inject
    EventInfoService eventInfoService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventInfoDTO get(@PathParam("id") Long id) {
        return eventInfoService.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventInfoDTO> getAll() {
        return eventInfoService.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public EventInfoDTO create(EventInfoDTO eventInfoDTO) {
        return eventInfoService.create(eventInfoDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Authenticated
    public EventInfoDTO update(@PathParam("id") Long id, EventInfoDTO eventInfoDTO) {
        return eventInfoService.update(id, eventInfoDTO);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        eventInfoService.delete(id);
        String message = "EventInfo with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
