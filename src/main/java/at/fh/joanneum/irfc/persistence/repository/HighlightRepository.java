package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.HighlightEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Set;

/**
 * @author david.krall@edu.fh-joanneum.at
 **/
@RequestScoped
public class HighlightRepository implements PanacheRepository<HighlightEntity> {
    public List<HighlightEntity> listWhereTitleLike(String like) {

        String searchInput = "%" + like + "%";

        return list("path like ?1", searchInput);
    }

    public boolean isHighlightIdInList(Set<HighlightEntity> highlightList, Long highlightId) {
        for (HighlightEntity highlight : highlightList) {
            if (highlight.getHighlightId().equals(highlightId)) {
                return true;
            }
        }
        return false;
    }
}
