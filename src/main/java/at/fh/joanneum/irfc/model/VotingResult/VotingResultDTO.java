package at.fh.joanneum.irfc.model.VotingResult;

import at.fh.joanneum.irfc.model.votingPartialResult.VotingPartialResultDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author https://github.com/GoldNova
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class VotingResultDTO {

    private Long votingResultId;
    private String title;
    private long endDate;
    private List<VotingPartialResultDTO> partialResults;

}
