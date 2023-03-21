package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author moe@softwaregaertner.at
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "event")
public class EventEntity {

  @Id
  @SequenceGenerator(
      name = "event_id_seq",
      sequenceName = "event_id_seq",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "event_id_seq"
  )
  @Column(name = "event_id")
  private Long eventId;

  @Column
  private String title;

//  @Column
//  private Image image;

  @Column(name = "start_date_time_in_utc")
  private long startDateTimeInUTC;
  @Column(name = "end_date_time_in_utc")
  private long endDateTimeInUTC;

//  @Column(name = "fk_event_location")
//  private EventLocationEntity eventLocation;

//  @Column(name = "fk_category")
//  private CategoryEntity eventLocation;


}