package at.fh.joanneum.irfc.persistence.entiy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author https://github.com/GoldNova
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "voting_result")
public class VotingResultEntity {

    @Id
    @SequenceGenerator(
            name = "voting_result_id_seq",
            sequenceName = "voting_result_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voting_result_id_seq"
    )
    @Column(name = "voting_result_id")
    private Long votingResultId;

    @Column(name="title")
    private String title;

    @Column(name = "end_date")
    private long endDate;

    @OneToOne(mappedBy="votingResult", cascade = CascadeType.ALL)
    private VotingEntity voting;

    @OneToMany(mappedBy = "votingResult")
    private Set<VotingPartialResultEntity> partialResults;
}