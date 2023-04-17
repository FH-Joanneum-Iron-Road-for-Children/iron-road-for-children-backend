package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vote")
public class VoteEntity {

    @Id
    @SequenceGenerator(
            name = "vote_id_seq",
            sequenceName = "vote_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vote_id_seq"
    )

    @JoinColumn(name = "fk_event_id", nullable = false)
    private Long eventId;

    @Column(name = "device_id")
    private String deviceId;
}