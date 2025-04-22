package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.socialMedia.SocialMediaDTO;
import at.fh.joanneum.irfc.service.SocialMediaService;
import io.quarkus.security.Authenticated;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
@Path("/socialMedias")
public class SocialMediaApi {

    @Inject
    SocialMediaService socialMediaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SocialMediaDTO> getAll() {
        return socialMediaService.getAll();
    }

    @GET
    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
    public SocialMediaDTO get(@PathParam("id") Long id) {
        return socialMediaService.get(id);
    }

    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public SocialMediaDTO create(SocialMediaDTO socialMediaDTO) {
        return socialMediaService.create(socialMediaDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Authenticated
    public SocialMediaDTO update(@PathParam("id") Long id, SocialMediaDTO socialMediaDTO) {

        return socialMediaService.update(id, socialMediaDTO);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        socialMediaService.delete(id);
        String message = "SocialMedia with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
