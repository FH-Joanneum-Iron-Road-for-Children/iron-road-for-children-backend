package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.vote.VoteDTO;
import at.fh.joanneum.irfc.service.VoteService;

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
@Path("/votes")
public class VoteApi {

    @Inject
    VoteService voteService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public VoteDTO create(VoteDTO voteDTO){
        return voteService.create(voteDTO);
    }





}