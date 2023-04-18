package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.model.picture.PictureMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import at.fh.joanneum.irfc.persistence.repository.PictureRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Objects.isNull;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class PictureService {
    @Inject
    PictureRepository pictureRepository;

    public PictureDTO get(Long id) {
        Optional<PictureEntity> byIdOptional = pictureRepository.findByIdOptional(id);
        if(byIdOptional.isEmpty()){
            throw new RuntimeException("Picture with id " + id + " not found");
        } else {
            PictureEntity byId = byIdOptional.get();
            return PictureMapper.INSTANCE.toDto(byId);
        }
    }

    public List<PictureDTO> search(String searchString) {
        List<PictureEntity> pictures = pictureRepository.listWhereTitleLike(searchString);
        if(pictures.isEmpty()){
            throw new RuntimeException("No Picture with search \"" + searchString + "\"  found");
        } else {
            List<PictureDTO> l = new ArrayList<PictureDTO>();
            for (PictureEntity p : pictures) {
                l.add(PictureMapper.INSTANCE.toDto(p));
            }
            return l;
        }
    }

    @Transactional
    public PictureDTO create(PictureDTO pictureDTO) {

        validateDto(pictureDTO);

        PictureEntity newEntity = new PictureEntity();
        setValues(pictureDTO, newEntity);
        pictureRepository.persist(newEntity);
        return PictureMapper.INSTANCE.toDto(newEntity);
    }

    private static void setValues(PictureDTO pictureDTO, PictureEntity newEntity) {
        newEntity.setTitle(pictureDTO.getTitle());
    }

    private static void validateDto (PictureDTO pictureDTO) {
        if(isNull(pictureDTO.getTitle()) || pictureDTO.getTitle().isBlank()){
            throw new RuntimeException("Title must be provided");
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!pictureRepository.deleteById(id)){
            throw new RuntimeException("Picture with id " + id + " not found");
        }
    }
}