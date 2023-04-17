package at.fh.joanneum.irfc.model.eventInfo;

import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
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
    private List<PictureEntity> pictures;
    public Long getEventInfoId() {
        return eventInfoId;
    }
    public List<PictureEntity> getPictures() {
        return pictures;
    }
    public String getInfoText() {
        return infoText;
    }
}