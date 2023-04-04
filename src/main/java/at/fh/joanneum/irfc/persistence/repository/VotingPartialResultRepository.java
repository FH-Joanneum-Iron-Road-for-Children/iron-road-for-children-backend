package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VotingPartialResultEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.RequestScoped;

/**
 * @author https://github.com/GoldNova
 **/

@RequestScoped //Oder ApplicationScoped?
public class VotingPartialResultRepository implements PanacheRepository<VotingPartialResultEntity> {
}
