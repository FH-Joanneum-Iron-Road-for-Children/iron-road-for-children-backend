package at.fh.joanneum.irfc.model.picture;


import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.vertx.codegen.annotations.Nullable;
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
    private EventInfoEntity eventInfo;
    private EventEntity event;
    public Long getPictureID() {
        return pictureID;
    }
    public String getTitle() {
        return title;
    }
    public String getPath() {
        return path;
    }
    public EventEntity getEvent() {
        return event;
    }
    public EventInfoEntity getEventInfo() {
        return eventInfo;
    }
}
