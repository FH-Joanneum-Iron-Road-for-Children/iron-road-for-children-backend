package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoMapper;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.model.picture.PictureMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import at.fh.joanneum.irfc.persistence.repository.EventInfoRepository;
import at.fh.joanneum.irfc.persistence.repository.PictureRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class EventInfoService {
    @Inject
    EventInfoRepository eventInfoRepository;
    @Inject
    PictureRepository pictureRepository;

    public EventInfoDTO get(Long id) {
        Optional<EventInfoEntity> byIdOptional = eventInfoRepository.findByIdOptional(id);
        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        } else {
            EventInfoEntity byId = byIdOptional.get();
            return EventInfoMapper.INSTANCE.toDto(byId);
        }
    }

    public List<EventInfoDTO> getAll() {
        return eventInfoRepository.listAll().stream()
                .map(EventInfoMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public EventInfoDTO create(EventInfoDTO eventInfoDTO) {
        checkDTOvalues(eventInfoDTO);
        EventInfoEntity newEntity = new EventInfoEntity();
        setValues(eventInfoDTO, newEntity);
        eventInfoRepository.persist(newEntity);
        return EventInfoMapper.INSTANCE.toDto(newEntity);
    }

    private static void checkDTOvalues(EventInfoDTO eventInfoDTO) {
        if(isNull(eventInfoDTO.getInfoText()) || eventInfoDTO.getInfoText().isBlank()){
            throw new RuntimeException("Info Text must be provided");
        }
    }

    @Transactional
    public EventInfoDTO update(Long id, EventInfoDTO eventInfoDTO) {
        Optional<EventInfoEntity> byIdOptional = eventInfoRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        } else {
            checkDTOvalues(eventInfoDTO);
            EventInfoEntity byId = byIdOptional.get();
            setValues(eventInfoDTO, byId);
            eventInfoRepository.persistAndFlush(byId);
            return EventInfoMapper.INSTANCE.toDto(byId);
        }
    }

    private void setValues(EventInfoDTO eventInfoDTO, EventInfoEntity newEntity) {
        newEntity.setInfoText(eventInfoDTO.getInfoText());

        Set<PictureEntity> pictures = newEntity.getPictures();
        if (pictures == null) {
            pictures = new HashSet<>();
        }

        Set<Long> dtoPictureIds = new HashSet<>();
        for (PictureDTO picture : eventInfoDTO.getPictures()) {
            dtoPictureIds.add(picture.getPictureId());
        }

        Iterator<PictureEntity> iterator = pictures.iterator();
        while (iterator.hasNext()) {
            PictureEntity pictureEntity = iterator.next();
            Long pictureId = pictureEntity.getPictureId();
            if (!dtoPictureIds.contains(pictureId)) {
                iterator.remove();
            }
        }

        for (PictureDTO picture : eventInfoDTO.getPictures()) {
            if (!this.pictureRepository.isPictureIdInList(pictures, picture.getPictureId())) {
                Optional<PictureEntity> pictureOptional = this.pictureRepository.findByIdOptional(picture.getPictureId());
                if (pictureOptional.isEmpty()) {
                    throw new RuntimeException("No Picture with id " + picture.getPictureId());
                } else {
                    PictureEntity pictureEntity = pictureOptional.get();
                    pictures.add(pictureEntity);
                }
            }
        }

        newEntity.setPictures(pictures);
    }

    @Transactional
    public void delete(Long id) {
        if(!eventInfoRepository.deleteById(id)){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        }

    }
}
