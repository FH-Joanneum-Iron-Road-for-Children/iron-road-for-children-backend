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
}
