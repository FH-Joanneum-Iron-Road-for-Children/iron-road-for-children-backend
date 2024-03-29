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
    @Column(name = "voting_id", nullable = false)
    private Long votingId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_editable", nullable = false)
    private boolean isEditable;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "voting")
    private VotingResultEntity votingResult;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "voting_event", joinColumns = @JoinColumn(name = "voting_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<EventEntity> events = new HashSet<>();

    @OneToMany(mappedBy = "voting")
    private Set<VoteEntity> votes;
}
