package at.fh.joanneum.irfc.model.VotingResult;

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
public class VotingResultDTO {

    private Long VotingResultID;
    String Title;
    long startDateTimeInUTC;
    ///List<VotingPartialResult> partialResults;

}
