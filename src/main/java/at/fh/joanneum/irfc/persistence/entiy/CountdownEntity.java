package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kainbacher Dominik
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "countdown")
public class CountdownEntity {

    @Id
    @SequenceGenerator(
            name = "countdown_id_seq",
            sequenceName = "countdown_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "countdown_id_seq"
    )
    @Column(name = "countdown_id", nullable = false)
    private Long countdownId;


    @Column(name = "end_date_time_in_utc", nullable = false)
    private long endDateTimeInUTC;

}
