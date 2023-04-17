package at.fh.joanneum.irfc.model.picture;


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
    public Long getPictureID() {
        return pictureID;
    }
    public String getTitle() {
        return title;
    }
    public String getPath() {
        return path;
    }
}
