package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author david.krall@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "highlight")
public class HighlightEntity {

    @Id
    @SequenceGenerator(name = "highlight_id_seq", sequenceName = "highlight_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "highlight_id_seq")
    @Column(name = "highlight_id")
    private Long highlightId;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;
}
