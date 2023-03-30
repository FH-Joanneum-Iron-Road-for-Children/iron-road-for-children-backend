package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import at.fh.joanneum.irfc.persistence.repository.EventInfoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public EventInfoDTO create(EventInfoDTO eventInfoDTOCreate) {
        EventInfoEntity newEntity = new EventInfoEntity();
        setValues(eventInfoDTOCreate, newEntity);
        eventInfoRepository.persist(newEntity);
        return EventInfoMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public EventInfoDTO update(Long id, EventInfoDTO eventInfoDTOUpdate) {
        Optional<EventInfoEntity> byIdOptional = eventInfoRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventInfo with id " + id + " not found");
        } else {
            EventInfoEntity byId = byIdOptional.get();
            setValues(eventInfoDTOUpdate, byId);
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
