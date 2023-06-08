package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VoteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/

@RequestScoped
public class VoteRepository implements PanacheRepository<VoteEntity> {

    @Inject
    EntityManager entityManager;

    public long countVotesByEventAndVoting(Long eventId, Long votingId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM vote WHERE event_id = ?1 AND voting_id = ?2");

        query.setParameter(1, eventId);
        query.setParameter(2, votingId);

        Object result = query.getSingleResult();

        if (result instanceof Number) {
            return ((Number) result).longValue();
        }

        return 0;
    }

    public long countVotesByVoting(Long votingId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM vote WHERE voting_id = ?1");

        query.setParameter(1, votingId);

        Object result = query.getSingleResult();

        if (result instanceof Number) {
            return ((Number) result).longValue();
        }

        return 0;
    }
}