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
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_picture", referencedColumnName = "picture_id")
  private PictureEntity picture;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_event_info", referencedColumnName = "event_info_id")
  private EventInfoEntity eventInfo;

  @Column(name = "start_date_time_in_utc")
  private long startDateTimeInUTC;
  @Column(name = "end_date_time_in_utc")
  private long endDateTimeInUTC;

  @ManyToOne
  @JoinColumn(name = "fk_event_location", nullable = false)
  private EventLocationEntity eventLocation;

  //  @Column(name = "fk_category")
  //  private CategoryEntity eventLocation;

  //bool isEditable; //TODO create Task and Implement
}
