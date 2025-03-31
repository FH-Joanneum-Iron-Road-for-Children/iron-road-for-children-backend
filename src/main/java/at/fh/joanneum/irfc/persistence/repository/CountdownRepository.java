package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.CountdownEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
public class CountdownRepository implements PanacheRepository<CountdownEntity> {
    @Inject
    EntityManager entityManager;


    public boolean isCountdownIdInList(Set<CountdownEntity> countdownList, Long countdownId) {
        for (CountdownEntity countdown : countdownList) {
            if (countdown.getCountdownId().equals(countdownId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCountdownEditable(Long countdownId) {
        Query query = entityManager.createNativeQuery("SELECT is_editable FROM voting WHERE voting_id in (SELECT voting_id FROM voting_countdown WHERE countdown_id = ?1) and is_editable = true;");
        query.setParameter(1, countdownId);

        List<Object> results = query.getResultList();

        if(results.isEmpty()) {
            return true;
        }

        if (results.get(0) instanceof Boolean) {
            return (Boolean) results.get(0);
        }

        return false;
    }
}
