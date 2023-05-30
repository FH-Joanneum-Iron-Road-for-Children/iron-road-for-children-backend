package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingRepository implements PanacheRepository<VotingEntity> {
}
