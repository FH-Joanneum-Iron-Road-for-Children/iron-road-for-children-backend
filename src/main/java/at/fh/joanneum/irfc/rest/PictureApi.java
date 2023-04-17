package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.service.PictureService;

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
@Path("/pictures")
public class PictureApi {
    @Inject
    PictureService pictureService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PictureDTO get(@PathParam("id") Long id){ return pictureService.get(id); }

    @GET
    @Path("/search/{searchString}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PictureDTO> getByTitle(@PathParam("searchString") String searchString){
        return pictureService.search(searchString);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PictureDTO create(PictureDTO pictureDTO){ return pictureService.create(pictureDTO); }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        pictureService.delete(id);
        String message = "Picture with id "+id+" deleted";
        return Response
                .status(200, message)
                .build();
    }
}
