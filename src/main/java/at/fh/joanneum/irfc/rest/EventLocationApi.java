package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.eventlocation.EventLocationDTO;
import at.fh.joanneum.irfc.service.EventLocationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
@Path("event-locations")
public class EventLocationApi {
    @Inject
    EventLocationService eventLocationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventLocationDTO> getAll() {
        return eventLocationService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventLocationDTO getById(@PathParam("id") Long id) {
        return eventLocationService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EventLocationDTO create(EventLocationDTO eventLocationDTO){
        return eventLocationService.create(eventLocationDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public EventLocationDTO update(@PathParam("id") Long id, EventLocationDTO eventLocationDTO){
        return eventLocationService.update(id, eventLocationDTO);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        eventLocationService.delete(id);
        String message = "EventLocation with id "+ id + "deleted";
        return Response
                .status(200, message)
                .build();
    }
}
