package at.fh.joanneum.irfc.model.playlist;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author max pfisterer
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class PlaylistDTO {
    private Long playlistId;
    private String title;
    private String spotifyPlaylistId;
}
