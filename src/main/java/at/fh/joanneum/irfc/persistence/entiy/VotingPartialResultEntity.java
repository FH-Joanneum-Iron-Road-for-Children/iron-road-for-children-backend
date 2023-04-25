package at.fh.joanneum.irfc.persistence.entiy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author https://github.com/GoldNova
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "voting_partial_result")
public class VotingPartialResultEntity {

    @Id

    @SequenceGenerator(
            name = "voting_partial_result_id_seq",
            sequenceName = "voting_partial_result_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voting_partial_result_id_seq"
    )
    @Column(name = "voting_partial_result_id")
    private Long votingPartialResultId;

    @Column(name="event_name")
    private String eventName;

    @Column
    private Double percentage;


}
