package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.service.EventService;

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
@Path("/events")
public class EventApi {

  @Inject
  EventService eventService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EventDTO> getAll(){
    return eventService.getAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public EventDTO create(EventDTO eventDTOCreate){
    return eventService.create(eventDTOCreate);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public EventDTO update(@PathParam("id") Long id, EventDTO eventDTOUpdate){
    return eventService.update(id, eventDTOUpdate);
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id){
    eventService.delete(id);
    String message = "Event with id "+id+" deleted";
    return Response
        .status(200, message)
        .build();
  }

}