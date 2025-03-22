package at.fh.joanneum.irfc.model.socialMedia;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Kainbacher Dominik
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class SocialMediaDTO {
    private Long socialMediatId;
    private String title;
    private String link;
}
