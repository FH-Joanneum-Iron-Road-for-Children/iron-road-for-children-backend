package at.fh.joanneum.irfc.persistence.repository;

import at.fh.joanneum.irfc.persistence.entiy.PlaylistEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

/**
 * @author Max Pfisterer
 **/
@RequestScoped
public class PlaylistRepository implements PanacheRepository<PlaylistEntity> {

}
