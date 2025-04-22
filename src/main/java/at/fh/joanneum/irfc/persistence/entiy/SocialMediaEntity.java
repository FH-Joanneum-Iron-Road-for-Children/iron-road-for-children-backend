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
@Table(name = "socialmedia")
public class SocialMediaEntity {

    @Id
    @SequenceGenerator(
            name = "socialmedia_id_seq",
            sequenceName = "socialmedia_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "socialmedia_id_seq"
    )
    @Column(name = "socialmedia_id")
    private Long socialMediaId;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;
}
