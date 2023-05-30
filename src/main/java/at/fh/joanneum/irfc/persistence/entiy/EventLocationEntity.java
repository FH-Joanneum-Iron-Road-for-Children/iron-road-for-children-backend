package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author moe@softwaregaertner.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "event_location")
public class EventLocationEntity {

    @Id
    @SequenceGenerator(
            name = "event_location_id_seq",
            sequenceName = "event_location_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_location_id_seq"
    )
    @Column(name = "event_location_id")
    private Long eventLocationId;

    @Column
    private String name;

    @OneToMany(mappedBy = "eventLocation")
    private Set<EventEntity> event;
}
