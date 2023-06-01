package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.Set;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingRepository implements PanacheRepository<VotingEntity> {
    public boolean containsEventId(VotingEntity voting, Long eventId) {
        Set<EventEntity> events = voting.getEvents();
        for (EventEntity event : events) {
            if (event.getEventId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }
}
