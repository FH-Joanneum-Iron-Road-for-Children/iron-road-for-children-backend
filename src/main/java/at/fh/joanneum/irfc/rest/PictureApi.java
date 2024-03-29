package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.service.PictureService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

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
    public PictureDTO get(@PathParam("id") Long id) {
        return pictureService.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PictureDTO> getAll() {
        return pictureService.getAll();
    }

//    @GET
//    @Path("/search/{searchString}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public List<PictureDTO> getByTitle(@PathParam("searchString") String searchString){
//        return pictureService.search(searchString);
//    }

    @GET
    @Path("rootpath")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRootPath() {
        return pictureService.getRootpath();
    }

    // there will be only MULTIPART as input with file and altText
    // (see: https://quarkus.io/guides/rest-client-multipart)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Authenticated
    public PictureDTO create(@MultipartForm MultipartBody data) {
        return pictureService.create(data);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        pictureService.delete(id);
        String message = "Picture with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
