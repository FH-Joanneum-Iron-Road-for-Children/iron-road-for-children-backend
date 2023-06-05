package at.fh.joanneum.irfc.model.votingPartialResult;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author https://github.com/GoldNova
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class VotingPartialResultDTO {

    private Long id;
    private String eventName;
    private Double percentage;

}
