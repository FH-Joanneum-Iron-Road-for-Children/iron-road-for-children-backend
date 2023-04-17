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
    @Column(name = "voting_id")
    private Long votingId;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "device_id")
    private String deviceId;
}