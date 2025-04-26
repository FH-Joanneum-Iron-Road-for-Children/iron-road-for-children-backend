package at.fh.joanneum.irfc.rest;

import at.fh.joanneum.irfc.model.playlist.PlaylistDTO;
import at.fh.joanneum.irfc.service.PlaylistService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

/**
 * @author Max Pfisterer
 **/
@RequestScoped
@Path("/playlist")
public class PlaylistApi {

    @Inject
    PlaylistService playlistService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlaylistDTO createOrUpdate(PlaylistDTO playlistDTO) {
        return playlistService.createOrUpdate(playlistDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistDTO get() {
        return playlistService.getSingle();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete() {
        boolean deleted = playlistService.delete();
        if (deleted) {
            return Response.ok("{\"message\":\"Playlist deleted\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"No playlist to delete\"}")
                    .build();
        }
    }

}

