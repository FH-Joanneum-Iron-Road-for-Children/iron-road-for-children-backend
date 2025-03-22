package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kainbacher Dominik
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "socialMedia")
public class SocialMediaEntity {

    @Id
    @SequenceGenerator(
            name = "socialMedia_id_seq",
            sequenceName = "socialMedia_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "socialMedia_id_seq"
    )
    @Column(name = "socialMedia_id", nullable = false)
    private Long socialMediaId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;
}
