package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.SocialMediaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

}
