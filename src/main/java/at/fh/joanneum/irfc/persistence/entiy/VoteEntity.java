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
@IdClass(VoteEntity.VoteEntityId.class)
@Table(name = "vote")
public class VoteEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_voting", nullable = false)
    private VotingEntity voting;

    @Id
    @Column(name = "event_id")
    private Long eventId;

    @Id
    @Column(name = "device_id")
    private String deviceId;

    public static class VoteEntityId implements Serializable {
        private VotingEntity voting;
        private Long eventId;
        private String deviceId;
    }
}