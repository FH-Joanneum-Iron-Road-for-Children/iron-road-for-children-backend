package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "vote_id", nullable = false)
    private Long voteId;


    @ManyToOne
    @JoinColumn(name = "fk_voting", nullable = false)
    private VotingEntity voting;

    @ManyToOne
    @JoinColumn(name = "fk_event", nullable = false)
    private EventEntity event;

    @Column(name = "device_id")
    private String deviceId;
    }

