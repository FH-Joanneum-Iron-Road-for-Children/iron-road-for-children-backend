package at.fh.joanneum.irfc.model.countdown;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Kainbacher Dominik
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class CountdownDTO {
    private Long countdownId;
    private long endDateTimeInUTC;
}
