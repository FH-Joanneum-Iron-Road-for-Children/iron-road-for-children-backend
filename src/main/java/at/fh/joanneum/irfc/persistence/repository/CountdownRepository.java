package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.CountdownEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
public class CountdownRepository implements PanacheRepository<CountdownEntity> {
    @Inject
    EntityManager entityManager;
}
