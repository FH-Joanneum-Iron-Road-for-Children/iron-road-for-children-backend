package at.fh.joanneum.irfc.model.vote;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class VoteDTO {

    Long votingId;
    Long eventId;
    String deviceId;
}