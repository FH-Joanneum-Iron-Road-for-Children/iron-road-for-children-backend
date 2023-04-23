package at.fh.joanneum.irfc.persistence.entiy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author https://github.com/GoldNova
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "votingResult")
public class VotingResultEntity {

    @Id
    @SequenceGenerator(
            name = "voting-result_id_seq",
            sequenceName = "voting-result_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voting-result_id_seq"
    )
    @Column(name = "voting-result_id")
    private Long votingResultId;

    @Column(name="title")
    private String title;

    @Column(name = "end-date")
    private long endDate;

//    @Column(nullable = false,
//    name = "partial-results-id")
//    @NotNull
//    @OneToOne
//    private List<parialResults> partialResults;


}