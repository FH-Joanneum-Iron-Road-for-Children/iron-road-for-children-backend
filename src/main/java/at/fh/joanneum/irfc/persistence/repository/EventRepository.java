package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
public class EventRepository implements PanacheRepository<EventEntity> {
}
