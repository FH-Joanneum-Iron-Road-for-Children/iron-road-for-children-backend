package at.fh.joanneum.irfc.model.gallery;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class GalleryDTO {
    private Long galleryId;
    private String altText;
    private String path;
}
