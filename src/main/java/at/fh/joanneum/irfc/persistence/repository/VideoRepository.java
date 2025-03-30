package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.VideoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

/**
 * @author Max Pfisterer
 **/
@RequestScoped
public class VideoRepository implements PanacheRepository<VideoEntity> {

    public Optional<VideoEntity> findByPathOptional(String path) {
        return find("path", path).firstResultOptional();
    }
}
