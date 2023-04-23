package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.service.VotingService;

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

}
