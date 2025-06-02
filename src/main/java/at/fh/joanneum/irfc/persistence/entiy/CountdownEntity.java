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
@Table(name = "countdown")
public class CountdownEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "countdown_id_seq"
    )
    @Column(name = "countdown_id", nullable = false)
    private Long countdownId;


    @Column(name = "end_date_time_in_utc", nullable = false)
    private long endDateTimeInUTC;
}
