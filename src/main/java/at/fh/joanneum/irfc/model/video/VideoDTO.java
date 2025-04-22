package at.fh.joanneum.irfc.model.video;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Max Pfisterer
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class VideoDTO {
    private Long videoId;
    private String altText;
    private String path;
}
