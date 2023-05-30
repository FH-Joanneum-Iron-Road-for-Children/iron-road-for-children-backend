package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.model.eventCategory.EventCategoryDTO;
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
  private PictureDTO picture;
  private EventInfoDTO eventInfo;
  private long startDateTimeInUTC;
  private long endDateTimeInUTC;
  private EventLocationDTO eventLocation;
  private EventCategoryDTO eventCategory;
  //private bool isEditable;  //TODO check if we need this
}
