package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.model.eventlocation.EventLocationDTO;
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
public class EventDTO {

    private Long eventId;
    String title;
    long startDateTimeInUTC;
    long endDateTimeInUTC;
    EventLocationDTO eventLocation;
//  CategoryEntity eventLocation;


}
