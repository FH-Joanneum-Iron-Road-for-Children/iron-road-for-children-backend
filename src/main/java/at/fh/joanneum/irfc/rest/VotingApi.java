package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.service.VotingService;
import org.apache.commons.lang3.NotImplementedException;

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
@Path("/votings")
public class VotingApi {

    @Inject
    VotingService votingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VotingDTO> getAll(){
        return votingService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public VotingDTO get(@PathParam("id") Long id){
        return votingService.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public VotingDTO create(VotingDTO votingDTOCreate){
        return votingService.create(votingDTOCreate);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VotingDTO update(@PathParam("id") Long id, VotingDTO votingResultDTO){
        return votingService.update(id, votingResultDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VotingDTO startVoting(@PathParam("id") Long id){
        throw new NotImplementedException();
        //TODO create function to setIsActive to true and set isEditable to false and set isEditable in all Events to false
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VotingDTO endVoting(@PathParam("id") Long id){
        throw new NotImplementedException();
        //TODO create function to setIsActive to false and generate votingResults
        //TODO also define if voting and or events should be editable after a voting
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        votingService.delete(id);
        String message = "Voting with id "+id+" deleted";
        return Response
                .status(200, message)
                .build();
    }


}
