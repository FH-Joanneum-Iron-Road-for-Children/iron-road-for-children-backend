package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.video.VideoDTO;
import at.fh.joanneum.irfc.service.VideoService;
import io.quarkus.security.Authenticated;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Max Pfisterer
 **/
@RequestScoped
@Path("/intro-video")
public class IntroVideoApi {

    @Inject
    VideoService videoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIntroVideo() {
        return videoService.getIntroVideo()
                .map(video -> Response.ok(video).build())
                .orElse(Response.noContent().build());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Authenticated
    public VideoDTO createIntroVideo(@MultipartForm MultipartBody data) {
        return videoService.createIntroVideo(data);
    }

    @DELETE
    @Authenticated
    public Response deleteIntroVideo() {
        videoService.deleteIntroVideo();
        String message = "Intro video was deleted";
        return Response
                .status(200, message)
                .build();
    }
}
