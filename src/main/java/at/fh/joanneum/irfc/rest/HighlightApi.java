package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.highlight.HighlightDTO;
import at.fh.joanneum.irfc.model.multipartbody.HighlightMultipartBody;
import at.fh.joanneum.irfc.service.HighlightService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author david.krall@edu.fh-joanneum.at
 **/
@RequestScoped
@Path("/highlights")
public class HighlightApi {
    @Inject
    HighlightService highlightService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HighlightDTO get(@PathParam("id") Long id) {
        return highlightService.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HighlightDTO> getAll() {
        return highlightService.getAll();
    }

    @GET
    @Path("rootpath")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRootPath() {
        return highlightService.getRootpath();
    }

    // there will be only MULTIPART as input with file and altText
    // (see: https://quarkus.io/guides/rest-client-multipart)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Authenticated
    public HighlightDTO create(@MultipartForm HighlightMultipartBody data) {
        return highlightService.create(data);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response delete(@PathParam("id") Long id) {
        highlightService.delete(id);
        String message = "Highlight with id " + id + " deleted";
        return Response
                .status(200, message)
                .build();
    }
}
