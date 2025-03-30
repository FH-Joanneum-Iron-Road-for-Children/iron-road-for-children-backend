package at.fh.joanneum.irfc.model.highlight;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author david.krall@edu.fh-joanneum.at
 **/
@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class HighlightDTO {
    private Long highlightId;
    private String altText;
    private String description;
    private String path;
}
