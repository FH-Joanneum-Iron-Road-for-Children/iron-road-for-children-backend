package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import at.fh.joanneum.irfc.persistence.repository.EventInfoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@RequestScoped
public class EventInfoService {
    @Inject
    EventInfoRepository eventInfoRepository;

    public EventInfoDTO get(Long id) {
        Optional<EventInfoEntity> byIdOptional = eventInfoRepository.findByIdOptional(id);
        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        } else {
            EventInfoEntity byId = byIdOptional.get();
            return EventInfoMapper.INSTANCE.toDto(byId);
        }
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
        if(eventInfoDTO.getPictures().size() < 1){
            throw new RuntimeException("At least one Picture needs to be added");
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

    private static void setValues(EventInfoDTO eventInfoDTOCreate, EventInfoEntity newEntity) {
        newEntity.setInfoText(eventInfoDTOCreate.getInfoText());
        newEntity.setPictures(eventInfoDTOCreate.getPictures());
    }

    @Transactional
    public void delete(Long id) {
        if(!eventInfoRepository.deleteById(id)){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        }

    }
}
