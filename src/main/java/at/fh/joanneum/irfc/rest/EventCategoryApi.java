package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.event.EventCategoryDTO;
import at.fh.joanneum.irfc.service.EventCategoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@RequestScoped
@Path("/eventCategories")
public class EventCategoryApi {

  @Inject
  EventCategoryService eventCategoryService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EventCategoryDTO> getAll(){
    return eventCategoryService.getAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public EventCategoryDTO create(EventCategoryDTO eventCategoryDTO){
    return eventCategoryService.create(eventCategoryDTO);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EventCategoryDTO get(@PathParam("id") Long id){
    return eventCategoryService.get(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public EventCategoryDTO update(@PathParam("id") Long id, EventCategoryDTO eventCategoryDTO){
    return eventCategoryService.update(id, eventCategoryDTO);
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id){
    eventCategoryService.delete(id);
    String message = "Event category with id "+id+" deleted";
    return Response
        .status(200, message)
        .build();
  }

}
