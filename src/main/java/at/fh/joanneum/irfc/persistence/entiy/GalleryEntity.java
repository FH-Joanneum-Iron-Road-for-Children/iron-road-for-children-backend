package at.fh.joanneum.irfc.persistence.entiy;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "gallery")
public class GalleryEntity {

    @Id
    @SequenceGenerator(
            name = "gallery_id_seq",
            sequenceName = "gallery_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gallery_id_seq"
    )
    @Column(name = "gallery_id")
    private Long galleryId;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "path")
    private String path;

}
