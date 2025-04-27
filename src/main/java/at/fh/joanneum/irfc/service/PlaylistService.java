package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.playlist.PlaylistDTO;
import at.fh.joanneum.irfc.model.playlist.PlaylistMapper;
import at.fh.joanneum.irfc.persistence.entiy.PlaylistEntity;
import at.fh.joanneum.irfc.persistence.repository.PlaylistRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static java.util.Objects.isNull;
/**
 * @author Max Pfisterer
 **/
@RequestScoped
public class PlaylistService {

    @Inject
    PlaylistRepository playlistRepository;

    @Transactional
    public PlaylistDTO createOrUpdate(PlaylistDTO playlistDTO) {
        validateDTOvalues(playlistDTO);

        PlaylistEntity existing = playlistRepository.findAll().firstResult();
        if (existing == null) {
            existing = new PlaylistEntity();
        }
        existing.setTitle(playlistDTO.getTitle());
        existing.setSpotifyPlaylistId(playlistDTO.getSpotifyPlaylistId());
        playlistRepository.persist(existing);

        return PlaylistMapper.INSTANCE.toDto(existing);
    }

    public PlaylistDTO getSingle() {
        PlaylistEntity entity = playlistRepository.findAll().firstResult();
        if (entity == null) {
            return null;
        }
        return PlaylistMapper.INSTANCE.toDto(entity);
    }


    @Transactional
    public boolean delete() {
        PlaylistEntity existing = playlistRepository.findAll().firstResult();
        if (existing != null) {
            playlistRepository.deleteAll();
            return true;
        }
        return false;
    }

    private static void validateDTOvalues(PlaylistDTO playlistDTO) {
        if (isNull(playlistDTO.getTitle()) || playlistDTO.getTitle().isBlank()) {
            throw new RuntimeException("Playlist title must not be null and not empty");
        }
        if (isNull(playlistDTO.getSpotifyPlaylistId()) || playlistDTO.getSpotifyPlaylistId().isBlank()) {
            throw new RuntimeException("Playlist spotify id must not be null and not empty");
        }
    }
}