package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_voting_result", referencedColumnName = "voting_result_id")
    private VotingResultEntity votingResult;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "voting_event",
            joinColumns = @JoinColumn(name = "voting_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<EventEntity> events = new HashSet<>();

    @OneToMany(mappedBy = "voting")
    private Set<VoteEntity> votes;
}
