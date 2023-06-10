package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Set;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
public class EventRepository implements PanacheRepository<EventEntity> {
    @Inject
    EntityManager entityManager;

    public boolean hasActiveVoting(Long eventId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM event WHERE event_id = ?1 AND EXISTS (SELECT 1 FROM voting_event ve INNER JOIN voting v ON ve.voting_id = v.voting_id WHERE ve.event_id = ?1 AND v.is_active = true)");

        query.setParameter(1, eventId);

        Object result = query.getSingleResult();

        if (result instanceof Number) {
            int count = ((Number) result).intValue();
            return count > 0;
        }

        return false;
    }

    public boolean isEventIdInList(Set<EventEntity> eventList, Long eventId) {
        for (EventEntity event : eventList) {
            if (event.getEventId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEventEditable(Long eventId) {
        Query query = entityManager.createNativeQuery("SELECT is_editable FROM voting WHERE voting_id in (SELECT voting_id FROM voting_event WHERE event_id = ?1) and is_editable = true;");
        query.setParameter(1, eventId);

        Object result = query.getSingleResult();

        if (result instanceof Boolean) {
            return (Boolean) result;
        }

        return false;
    }
}
