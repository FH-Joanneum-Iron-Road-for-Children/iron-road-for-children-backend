package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
  @Column(name = "event_id", nullable = false)
  private Long eventId;

  @Column(nullable = false)
  private String title;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_event_info", referencedColumnName = "event_info_id")
  private EventInfoEntity eventInfo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_picture", referencedColumnName = "picture_id")
  private PictureEntity picture;

  @Column(name = "start_date_time_in_utc", nullable = false)
  private long startDateTimeInUTC;

  @Column(name = "end_date_time_in_utc", nullable = false)
  private long endDateTimeInUTC;

  @ManyToOne
  @JoinColumn(name = "fk_event_location", nullable = false)
  private EventLocationEntity eventLocation;

  @ManyToOne
  @JoinColumn(name = "fk_event_category", nullable = false)
  private EventCategoryEntity eventCategory;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
  private Set<VotingEntity> votings = new HashSet<>();

  @OneToMany(mappedBy = "event")
  private Set<VoteEntity> votes;
}
