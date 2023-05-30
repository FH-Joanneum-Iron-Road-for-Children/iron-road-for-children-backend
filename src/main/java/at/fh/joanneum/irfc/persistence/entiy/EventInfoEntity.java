package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "event_info")
public class EventInfoEntity {

    @Id
    @SequenceGenerator(
            name = "event_info_id_seq",
            sequenceName = "event_info_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_info_id_seq"
    )
    @Column(name = "event_info_id")
    private Long eventInfoId;

    @Column(name = "event_info_text")
    private String infoText;

    @OneToMany(mappedBy = "eventInfo")
    private Set<PictureEntity> pictures;

    @OneToOne(mappedBy="eventInfo", cascade = CascadeType.ALL)
    private EventEntity eventEntity;
}