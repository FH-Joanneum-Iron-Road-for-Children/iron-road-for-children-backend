package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.model.picture.PictureMapper;
import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import at.fh.joanneum.irfc.persistence.repository.PictureRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class PictureService {
    @Inject
    PictureRepository pictureRepository;

    @ConfigProperty(name="pictures.root_path")
    String pictureRootPath;

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

    private static void setValues(PictureDTO pictureDTO, PictureEntity newEntity) {
        newEntity.setAltText(pictureDTO.getAltText());
        newEntity.setPath(pictureDTO.getPath());
    }

    private static void validateDto (PictureDTO pictureDTO) {
        if(isNull(pictureDTO.getAltText()) || pictureDTO.getAltText().isBlank()){
            throw new RuntimeException("Alt-Text must be provided");
        }
    }

    @Transactional
    public void delete(Long id) {
        var pictureDto = get(id);
        try
        {
            File f= new File(pictureDto.getPath());
            if(!f.delete())
            {
                throw new RuntimeException("Picture with Path " + pictureDto.getPath() + " could not be deleted");
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("Something went wrong deleting picture with path " + pictureDto.getPath() + ": " + e.getMessage());
        }
        if(!pictureRepository.deleteById(id)){
            throw new RuntimeException("Picture with id " + id + " not found");
        }
    }

    @Transactional
    public PictureDTO create(MultipartBody data)  {
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setAltText(data.getAltText());
        validateDto(pictureDTO);

        PictureEntity newEntity = new PictureEntity();

        try {
            //TODO add basePath to  config
            //TODO add folder from root where to add pictures
            File targetFile = new File(UUID.randomUUID() + "." + data.getFileEndingType().name().toLowerCase());
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(data.file.readAllBytes());
            outStream.close();
            pictureDTO.setPath(targetFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException("Error storing Image");
        }

        setValues(pictureDTO, newEntity);

        pictureRepository.persist(newEntity);
        return PictureMapper.INSTANCE.toDto(newEntity);
    }

    public String getRootpath() {
        return pictureRootPath;
    }
}
