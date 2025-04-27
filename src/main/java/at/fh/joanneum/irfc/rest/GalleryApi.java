package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.gallery.GalleryDTO;
import at.fh.joanneum.irfc.service.GalleryService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@RequestScoped
@Path("/highlights")
public class GalleryApi {

    @Inject
    GalleryService galleryService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GalleryDTO get(@PathParam("id") Long id) {
        return galleryService.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GalleryDTO> getAll() {
        return galleryService.getAll();
    }

    @GET
    @Path("rootpath")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRootPath() {
        return galleryService.getRootpath();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Authenticated
    public GalleryDTO create(@MultipartForm MultipartBody data) {
        return galleryService.create(data);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        galleryService.delete(id);
        String message = "Gallery with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
