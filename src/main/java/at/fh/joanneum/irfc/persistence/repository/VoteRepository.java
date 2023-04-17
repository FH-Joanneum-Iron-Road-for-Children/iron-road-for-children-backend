package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VoteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@RequestScoped
public class VoteRepository implements PanacheRepository<VoteEntity> {
}