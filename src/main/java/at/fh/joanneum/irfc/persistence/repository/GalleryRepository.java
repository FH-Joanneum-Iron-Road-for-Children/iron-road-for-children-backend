package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.GalleryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Set;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@RequestScoped
public class GalleryRepository implements PanacheRepository<GalleryEntity> {
    public List<GalleryEntity> listWhereTitleLike(String like) {

        String searchInput = "%" + like + "%";

        return list("path like ?1", searchInput);
    }

    public boolean isGalleryIdInList(Set<GalleryEntity> galleryList, Long galleryId) {
        for (GalleryEntity gallery : galleryList) {
            if (gallery.getGalleryId().equals(galleryId)) {
                return true;
            }
        }
        return false;
    }
}
