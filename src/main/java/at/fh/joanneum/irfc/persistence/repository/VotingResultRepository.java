package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VotingResultEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author https://github.com/GoldNova
 **/

@RequestScoped
public class VotingResultRepository implements PanacheRepository<VotingResultEntity> {

    @Inject
    EntityManager entityManager;
    public void NullifyFKVoting(Long votingId) {
        entityManager.createNativeQuery("UPDATE voting_result SET fk_voting = null WHERE fk_voting = :votingId")
                .setParameter("votingId", votingId)
                .executeUpdate();
    }
}
