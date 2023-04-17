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
  String title;
  EventInfoDTO eventInfo;
  PictureDTO picture;
  long startDateTimeInUTC;
  long endDateTimeInUTC;
  EventLocationDTO eventLocation;
  //EventCategoryDTO eventCategory;
  //bool isEditable;
}
