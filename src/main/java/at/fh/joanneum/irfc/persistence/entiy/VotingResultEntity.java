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
@Table(name = "VotingResult")

public class VotingResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting-result_id")
    private Long id;

    @Column(nullable = false, name="title")
    @NotBlank
    private String title;

    @Column(nullable = false,
            name = "end-date")
    @NotNull
    private long endDate;

//    @Column(nullable = false,
//    name = "partial-results-id")
//    @NotNull
//    @OneToOne
//    private List<parialResults> partialResults;


}