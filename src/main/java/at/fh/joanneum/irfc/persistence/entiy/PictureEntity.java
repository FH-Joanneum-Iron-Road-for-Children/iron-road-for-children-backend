package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

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

    @Column
    private String title;


    @ManyToOne
    @JoinColumn(name = "fk_event_info", nullable = false)
    private EventInfoEntity eventInfoEntity;

}