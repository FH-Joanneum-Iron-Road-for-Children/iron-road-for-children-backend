package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.List;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class PictureRepository implements PanacheRepository<PictureEntity> {
    public List<PictureEntity> listWhereTitleLike(String like) {

        String searchInput = "%" + like + "%";

        return list("title like ?1", searchInput );
    }
}
