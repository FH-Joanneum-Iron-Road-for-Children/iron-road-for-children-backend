package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author max pfisterer
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "playlist")
public class PlaylistEntity {

    @Id
    @SequenceGenerator(
            name = "playlist_id_seq",
            sequenceName = "playlist_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "playlist_id_seq"
    )
    @Column(name = "playlist_id", nullable = false)
    private Long playlistId;

    @Column(nullable = false)
    private String title;

    @Column(name= "spotify_playlist_id", nullable = false)
    private String spotifyPlaylistId;
}