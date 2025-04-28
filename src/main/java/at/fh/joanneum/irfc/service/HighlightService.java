package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.highlight.HighlightDTO;
import at.fh.joanneum.irfc.model.highlight.HighlightMapper;
import at.fh.joanneum.irfc.model.multipartbody.HighlightMultipartBody;
import at.fh.joanneum.irfc.persistence.entiy.HighlightEntity;
import at.fh.joanneum.irfc.persistence.repository.HighlightRepository;
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
 * @author david.krall@edu.fh-joanneum.at
 **/
@RequestScoped
public class HighlightService {
    @Inject
    HighlightRepository highlightRepository;

    @ConfigProperty(name = "pictures.root_path")
    String highlightRootPath;

    @ConfigProperty(name = "pictures.url") // TODO replace this with an .env var
    String highlightUrl;

    public HighlightDTO get(Long id) {
        Optional<HighlightEntity> byIdOptional = highlightRepository.findByIdOptional(id);
        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Highlight with id " + id + " not found");
        } else {
            HighlightEntity byId = byIdOptional.get();
            return HighlightMapper.INSTANCE.toDto(byId);
        }
    }

    public List<HighlightDTO> getAll() {
        return highlightRepository.listAll().stream()
                .map(HighlightMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<HighlightDTO> search(String searchString) {
        List<HighlightEntity> highlights = highlightRepository.listWhereTitleLike(searchString);
        if (highlights.isEmpty()) {
            throw new RuntimeException("No Highlight with search \"" + searchString + "\"  found");
        } else {
            List<HighlightDTO> l = new ArrayList<HighlightDTO>();
            for (HighlightEntity p : highlights) {
                l.add(HighlightMapper.INSTANCE.toDto(p));
            }
            return l;
        }
    }

    private static void setValues(HighlightDTO highlightDTO, HighlightEntity newEntity) {
        newEntity.setAltText(highlightDTO.getAltText());
        newEntity.setDescription(highlightDTO.getDescription());
        newEntity.setPath(highlightDTO.getPath());
    }

    private static void validateDto(HighlightDTO highlightDTO) {
        if (isNull(highlightDTO.getAltText()) || highlightDTO.getAltText().isBlank()) {
            throw new RuntimeException("Alt-Text must be provided");
        }
        if (isNull(highlightDTO.getDescription()) || highlightDTO.getDescription().isBlank()) {
            throw new RuntimeException("Description must be provided");
        }
    }

    @Transactional
    public void delete(Long id) {
        var highlightDTO = get(id);
        try {

            File f = new File(highlightDTO.getPath().replace(highlightUrl, highlightRootPath));
            if (!f.delete()) {
                throw new RuntimeException("Highlight with Path " + highlightDTO.getPath() + " could not be deleted");
            }
        } catch (Exception e) {
            throw new RuntimeException(
                    "Something went wrong deleting the hightlight with path " + highlightDTO.getPath() + ": "
                            + e.getMessage());
        }
        if (!highlightRepository.deleteById(id)) {
            throw new RuntimeException("Highlight with id " + id + " not found");
        }
    }

    @Transactional
    public HighlightDTO create(HighlightMultipartBody data) {
        HighlightDTO highlightDTO = new HighlightDTO();
        highlightDTO.setAltText(data.getAltText());
        highlightDTO.setDescription(data.getDescription());
        validateDto(highlightDTO);

        HighlightEntity newEntity = new HighlightEntity();

        try {
            String fileName = UUID.randomUUID() + "." + data.getFileEndingType().name().toLowerCase();
            File targetFile = new File(highlightRootPath + fileName);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(data.file.readAllBytes());
            outStream.close();
            highlightDTO.setDescription(data.description);
            highlightDTO.setPath(highlightUrl + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error storing Image" + e.getMessage());
        }

        setValues(highlightDTO, newEntity);

        highlightRepository.persist(newEntity);
        return HighlightMapper.INSTANCE.toDto(newEntity);
    }

    public String getRootpath() {
        return highlightRootPath;
    }
}
