package at.fh.joanneum.irfc.persistence.entiy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "VotingResult")

public class VotingResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "votingResult_id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false,
            name = "end_date")
    @NotNull
    private long endDate;

//    @Column(nullable = false,
//    name = "partial_results_id")
//    @NotNull
//    @OneToOne
//    private List<parialResults> partialResults;


}