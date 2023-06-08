package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class EventInfoRepository implements PanacheRepository<EventInfoEntity> {
}
