package at.fh.joanneum.irfc.model.voting;


import at.fh.joanneum.irfc.model.VotingResult.VotingResultDTO;
import at.fh.joanneum.irfc.model.event.EventDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jdk.jfr.Event;
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
public class VotingDTO {
    private Long votingId;
    private String title;
    private boolean isActive;
    private boolean isEditable;
    private List<EventDTO> events;
    private VotingResultDTO votingResult;
}
