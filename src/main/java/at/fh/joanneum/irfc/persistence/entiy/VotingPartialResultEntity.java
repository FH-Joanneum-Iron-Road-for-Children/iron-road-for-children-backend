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
@Table(name = "VotingPartialResult")
public class VotingPartialResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String eventName;

    @Column(nullable = false)
    @NotNull
    private Double percentage;


}
