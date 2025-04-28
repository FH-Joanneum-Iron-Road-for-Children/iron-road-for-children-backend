package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.socialMedia.SocialMediaDTO;
import at.fh.joanneum.irfc.model.socialMedia.SocialMediaMapper;
import at.fh.joanneum.irfc.persistence.entiy.*;
import at.fh.joanneum.irfc.persistence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
public class SocialMediaService {

    @Inject
    SocialMediaRepository socialMediaRepository;

    public List<SocialMediaDTO> getAll() {
        return socialMediaRepository.listAll().stream()
                .map(SocialMediaMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public SocialMediaDTO get(Long id) {
        SocialMediaEntity socialMediaEntity = socialMediaRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("SocialMedia with id " + id + " not found"));

        return SocialMediaMapper.INSTANCE.toDto(socialMediaEntity);
    }

    @Transactional
    public SocialMediaDTO create(SocialMediaDTO socialMediaDTO) {
        SocialMediaEntity newEntity = new SocialMediaEntity();
        setValues(socialMediaDTO, newEntity);
        socialMediaRepository.persist(newEntity);
        return SocialMediaMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public SocialMediaDTO update(Long id, SocialMediaDTO socialMediaDTO) {
        Optional<SocialMediaEntity> byIdOptional = socialMediaRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Event with id " + id + " not found");
        } else {
            SocialMediaEntity byId = byIdOptional.get();
            setValues(socialMediaDTO, byId);
            socialMediaRepository.persistAndFlush(byId);
            return SocialMediaMapper.INSTANCE.toDto(byId);
        }
    }

    @Transactional
    public void delete(Long id) {

        if (!socialMediaRepository.deleteById(id)) {
            throw new RuntimeException("SocialMedia with id " + id + " not found");
        }
    }

    private void setValues(SocialMediaDTO socialMediaDTO, SocialMediaEntity socialMediaEntity) {
        socialMediaEntity.setTitle(socialMediaDTO.getTitle());
        socialMediaEntity.setLink(socialMediaDTO.getLink());
    }
}
