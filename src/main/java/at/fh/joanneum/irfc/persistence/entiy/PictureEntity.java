package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "picture")
public class PictureEntity {

    @Id
    @SequenceGenerator(
            name = "picture_id_seq",
            sequenceName = "picture_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picture_id_seq"
    )
    @Column(name = "picture_id")
    private Long pictureId;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "path")
    private String path;

    @ManyToMany(mappedBy = "pictures")
    private Set<EventInfoEntity> eventInfos = new HashSet<>();
    
}