package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class EventInfoRepository implements PanacheRepository<EventInfoEntity> {
    @Inject
    EntityManager entityManager;
    public boolean hasActiveVoting(Long eventInfoId) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM event WHERE fk_event_info = ?1 AND EXISTS (SELECT 1 FROM voting_event ve INNER JOIN voting v ON ve.voting_id = v.voting_id WHERE ve.event_id = (SELECT event_id FROM event WHERE fk_event_info = ?1)  AND v.is_active = true)");

        query.setParameter(1, eventInfoId);

        Object result = query.getSingleResult();

        if (result instanceof Number) {
            int count = ((Number) result).intValue();
            return count > 0;
        }

        return false;
    }
}
