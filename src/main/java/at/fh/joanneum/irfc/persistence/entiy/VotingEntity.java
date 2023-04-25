package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author https://github.com/GoldNova
 **/
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "voting")
public class VotingEntity {

    @Id
    @SequenceGenerator(
            name = "voting_id_seq",
            sequenceName = "voting_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "voting_id_seq"
    )
    @Column(name = "voting_id")
    private Long votingId;

    @Column(name = "title")
    private String title;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_editable")
    private boolean isEditable;

//    @Column(name = "events")
//    private List<Event> events;
//
//    @Column(name = "eventCategoryId")
//    private long eventCategoryId;
//
//    @Column(name = "votingResult")
//    private VotingResult votingResult;

}
