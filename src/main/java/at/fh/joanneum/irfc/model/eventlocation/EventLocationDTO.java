package at.fh.joanneum.irfc.model.eventlocation;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author moe@softwaregaertner.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class EventLocationDTO {
    private Long eventLocationId;
    private String name;
}
