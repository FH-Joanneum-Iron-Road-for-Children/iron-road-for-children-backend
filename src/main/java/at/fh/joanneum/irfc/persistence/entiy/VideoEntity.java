package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Max Pfisterer
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "video")
public class VideoEntity {
    @Id
    @SequenceGenerator(
            name = "video_id_seq",
            sequenceName = "video_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "video_id_seq"
    )
    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "path")
    private String path;
}
