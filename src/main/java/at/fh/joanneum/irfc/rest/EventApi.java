package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.service.EventService;
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
@Path("/events")
public class EventApi {

  @Inject
  EventService eventService;

  //TODO remove hello and claimNames endpoint

  @GET
  @Path("/secure")
  @RolesAllowed("admin")
  public String secureEndpoint() {
    // This endpoint requires the "admin" role.
    return "Hello, authenticated user!";
  }
  @GET
  @Path("hello")
  @Produces(MediaType.TEXT_PLAIN)
  @PermitAll
  public String hello(@Context SecurityContext securityContext) {
    final var user = (DefaultJWTCallerPrincipal) securityContext.getUserPrincipal();
    if (user == null){
      return "Hello Anonymous User";
    }
    final var email = user.getClaim("email");
    return "hello " + email;
  }

  @GET
  @Path("claim_names")
  @Produces(MediaType.TEXT_PLAIN)
  @PermitAll
  public Set<String> claimNames(@Context SecurityContext securityContext) {
    final var user = (DefaultJWTCallerPrincipal) securityContext.getUserPrincipal();
    if (user == null)
      return Set.of();
    return user.getClaimNames();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EventDTO> getAll(){
    return eventService.getAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EventDTO get(@PathParam("id") Long id){
    return eventService.get(id);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public EventDTO create(EventDTO eventDTO){
    return eventService.create(eventDTO);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public EventDTO update(@PathParam("id") Long id, EventDTO eventDTO){
    return eventService.update(id, eventDTO);
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
