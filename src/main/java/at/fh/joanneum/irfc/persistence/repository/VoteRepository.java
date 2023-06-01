package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VoteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@RequestScoped
public class VoteRepository implements PanacheRepository<VoteEntity> {
    public long countVotesByEventAndVoting(Long eventId, Long votingId) {
        return count("event.id = ?1 and voting.id = ?2", eventId, votingId);
    }

    public long countVotesByVoting(Long votingId) {
        return count("voting.id = ?1", votingId);
    }
}