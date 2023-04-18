package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.model.eventlocation.EventLocationDTO;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
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
  private String title;
  private EventInfoDTO eventInfo;
  private PictureDTO picture;
  private long startDateTimeInUTC;
  private long endDateTimeInUTC;
  private EventLocationDTO eventLocation;
  //private EventCategoryDTO eventCategory;
  //private bool isEditable;
}
