package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.SocialMediaEntity;
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
public class SocialMediaRepository implements PanacheRepository<SocialMediaEntity> {
    @Inject
    EntityManager entityManager;


    public boolean isSocialMediaIdInList(Set<SocialMediaEntity> socialMediaList, Long socialMediaId) {
        for (SocialMediaEntity socialMedia : socialMediaList) {
            if (socialMedia.getSocialMediaId().equals(socialMediaId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSocialMediaEditable(Long socialMediaId) {
        Query query = entityManager.createNativeQuery("SELECT is_editable FROM voting WHERE voting_id in (SELECT voting_id FROM voting_socialMedia WHERE socialMedia_id = ?1) and is_editable = true;");
        query.setParameter(1, socialMediaId);

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
