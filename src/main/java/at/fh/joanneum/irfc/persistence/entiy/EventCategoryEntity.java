package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "event_category")
public class EventCategoryEntity {

    @Id
    @SequenceGenerator(
            name = "event_category_id_seq",
            sequenceName = "event_category_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_category_id_seq"
    )
    @Column(name = "event_category_id")
    private Long eventCategoryId;

    @Column(name = "event_category_name")
    private String name;
}
