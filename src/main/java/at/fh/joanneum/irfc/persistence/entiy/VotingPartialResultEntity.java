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
@Table(name = "VotingPartialResult")
public class VotingPartialResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="voting_partial_result_id")
    private Long id;

    @Column(nullable = false, name="event_name")
    @NotBlank
    private String eventName;

    @Column(nullable = false)
    @NotNull
    private Double percentage;


}
