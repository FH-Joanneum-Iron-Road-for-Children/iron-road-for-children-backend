package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.VotingResult.VotingResultDTO;
import at.fh.joanneum.irfc.service.VotingResultService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
@Path("/votingresult")
public class VotingResultApi {

    @Inject
    VotingResultService votingResultService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VotingResultDTO> getAll(){
        return votingResultService.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public VotingResultDTO create(VotingResultDTO votingResultDTOCreate){
        return votingResultService.create(votingResultDTOCreate);
    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{id}")
//    public EventDTO update(@PathParam("id") Long id, EventDTO eventDTOUpdate){
//        return eventService.update(id, eventDTOUpdate);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response delete(@PathParam("id") Long id){
//        eventService.delete(id);
//        String message = "Event with id "+id+" deleted";
//        return Response
//                .status(200, message)
//                .build();
//    }

}
