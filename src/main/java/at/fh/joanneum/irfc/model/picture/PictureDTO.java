package at.fh.joanneum.irfc.model.picture;


import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class PictureDTO {
    private Long pictureID;
    private String title;
    private String path;
//    private EventInfoDTO eventInfo;
//    private EventDTO event;
}
