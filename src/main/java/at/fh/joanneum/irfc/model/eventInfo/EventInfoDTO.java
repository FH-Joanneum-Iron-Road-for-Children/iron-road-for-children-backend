package at.fh.joanneum.irfc.model.eventInfo;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class EventInfoDTO {
    private Long eventInfoId;
    private String infoText;
    private List<PictureDTO> pictures;
    private EventDTO event;
}