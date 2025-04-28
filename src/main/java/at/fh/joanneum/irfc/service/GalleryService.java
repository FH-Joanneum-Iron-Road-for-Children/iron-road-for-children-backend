package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.gallery.GalleryDTO;
import at.fh.joanneum.irfc.model.gallery.GalleryMapper;
import at.fh.joanneum.irfc.persistence.entiy.GalleryEntity;
import at.fh.joanneum.irfc.persistence.repository.GalleryRepository;
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
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@RequestScoped
public class GalleryService {
    @Inject
    GalleryRepository galleryRepository;

    @ConfigProperty(name = "pictures.root_path")
    String galleryRootPath;

    @ConfigProperty(name = "pictures.url") //TODO replace this with an .env var
    String galleryUrl;

    public GalleryDTO get(Long id) {
        Optional<GalleryEntity> byIdOptional = galleryRepository.findByIdOptional(id);
        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Gallery with id " + id + " not found");
        } else {
            GalleryEntity byId = byIdOptional.get();
            return GalleryMapper.INSTANCE.toDto(byId);
        }
    }


    public List<GalleryDTO> getAll() {
        return galleryRepository.listAll().stream()
                .map(GalleryMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<GalleryDTO> search(String searchString) {
        List<GalleryEntity> gallerys = galleryRepository.listWhereTitleLike(searchString);
        if (gallerys.isEmpty()) {
            throw new RuntimeException("No Gallery with search \"" + searchString + "\"  found");
        } else {
            List<GalleryDTO> l = new ArrayList<GalleryDTO>();
            for (GalleryEntity p : gallerys) {
                l.add(GalleryMapper.INSTANCE.toDto(p));
            }
            return l;
        }
    }

    private static void setValues(GalleryDTO galleryDTO, GalleryEntity newEntity) {
        newEntity.setAltText(galleryDTO.getAltText());
        newEntity.setPath(galleryDTO.getPath());
    }

    private static void validateDto(GalleryDTO galleryDTO) {
        if (isNull(galleryDTO.getAltText()) || galleryDTO.getAltText().isBlank()) {
            throw new RuntimeException("Alt-Text must be provided");
        }
    }

    @Transactional
    public void delete(Long id) {
        var galleryDto = get(id);
        try {

            File f = new File(galleryDto.getPath().replace(galleryUrl, galleryRootPath));
            if (!f.delete()) {
                throw new RuntimeException("Gallery with Path " + galleryDto.getPath() + " could not be deleted");
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong deleting gallery with path " + galleryDto.getPath() + ": " + e.getMessage());
        }
        if (!galleryRepository.deleteById(id)) {
            throw new RuntimeException("Gallery with id " + id + " not found");
        }
    }

    @Transactional
    public GalleryDTO create(MultipartBody data) {
        GalleryDTO galleryDTO = new GalleryDTO();
        galleryDTO.setAltText(data.getAltText());
        validateDto(galleryDTO);

        GalleryEntity newEntity = new GalleryEntity();

        try {
            String fileName = UUID.randomUUID() + "." + data.getFileEndingType().name().toLowerCase();
            File targetFile = new File(galleryRootPath + fileName);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(data.file.readAllBytes());
            outStream.close();
            galleryDTO.setPath(galleryUrl + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error storing Image" + e.getMessage());
        }

        setValues(galleryDTO, newEntity);

        galleryRepository.persist(newEntity);
        return GalleryMapper.INSTANCE.toDto(newEntity);
    }

    public String getRootpath() {
        return galleryRootPath;
    }
}
