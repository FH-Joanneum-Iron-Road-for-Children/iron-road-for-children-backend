package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Set;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class PictureRepository implements PanacheRepository<PictureEntity> {
    public List<PictureEntity> listWhereTitleLike(String like) {

        String searchInput = "%" + like + "%";

        return list("path like ?1", searchInput);
    }

    public boolean isPictureIdInList(Set<PictureEntity> pictureList, Long pictureId) {
        for (PictureEntity picture : pictureList) {
            if (picture.getPictureId().equals(pictureId)) {
                return true;
            }
        }
        return false;
    }
}
