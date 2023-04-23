package at.fh.joanneum.irfc.model.voting;


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
public class VotingDTO {

    private Long votingId;
    private String title;
    private boolean isActive;
    private boolean isEditable;

//    private List<Event> events;
//    private long eventCategoryId;
//    private VotingResult votingResult;

}
