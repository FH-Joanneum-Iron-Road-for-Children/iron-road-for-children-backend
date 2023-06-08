package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Set;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingRepository implements PanacheRepository<VotingEntity> {

    @Inject
    EntityManager entityManager;

    public boolean containsEventId(VotingEntity voting, Long eventId) {
        Set<EventEntity> events = voting.getEvents();
        for (EventEntity event : events) {
            if (event.getEventId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isVotingActive(Long votingId) {
        Query query = entityManager.createNativeQuery("SELECT is_active FROM voting WHERE voting_id = ?1");
        query.setParameter(1, votingId);

        Object result = query.getSingleResult();

        if (result instanceof Boolean) {
            return (Boolean) result;
        }

        return false;
    }

    public boolean isVotingEditable(Long votingId) {
        Query query = entityManager.createNativeQuery("SELECT is_editable FROM voting WHERE voting_id = ?1");
        query.setParameter(1, votingId);

        Object result = query.getSingleResult();

        if (result instanceof Boolean) {
            return (Boolean) result;
        }

        return false;
    }
}
