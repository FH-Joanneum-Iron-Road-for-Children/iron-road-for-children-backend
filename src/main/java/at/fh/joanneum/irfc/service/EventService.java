package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.model.eventInfo.EventInfoDTO;
import at.fh.joanneum.irfc.persistence.entiy.*;
import at.fh.joanneum.irfc.persistence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
public class EventService {

  @Inject
  EventRepository eventRepository;

  @Inject
  EventLocationRepository eventLocationRepository;
  @Inject
  PictureRepository pictureRepository;
  @Inject
  EventInfoRepository eventInfoRepository;
  @Inject
  EventCategoryRepository eventCategoryRepository;
  @Inject
  EventInfoService eventInfoService;

  public List<EventDTO> getAll() { //TODO throws exception (pls fix)
    return eventRepository.listAll().stream()
        .map(EventMapper.INSTANCE::toDto)
        .collect(Collectors.toUnmodifiableList());
  }

  public EventDTO get(Long id) {
    EventEntity eventEntity = eventRepository.findByIdOptional(id)
        .orElseThrow(() -> new RuntimeException("Event with id " + id + " not found"));

    return EventMapper.INSTANCE.toDto(eventEntity);
  }

  @Transactional
  public EventDTO create(EventDTO eventDTO) {
    checkDTOvalues(eventDTO);

    EventEntity newEntity = new EventEntity();
    setValues(eventDTO, newEntity);
    eventRepository.persist(newEntity);
    return EventMapper.INSTANCE.toDto(newEntity);
  }

  @Transactional
  public EventDTO update(Long id, EventDTO eventDTO) {
    Optional<EventEntity> byIdOptional = eventRepository.findByIdOptional(id);

    checkDTOvalues(eventDTO);

    if(byIdOptional.isEmpty()){
      throw new RuntimeException("Event with id " + id + " not found");
    } else if(eventRepository.hasActiveVoting(id)) {
      throw new RuntimeException("There is an active Voting running.");
    }else {
      EventEntity byId = byIdOptional.get();
      setValues(eventDTO, byId);
      eventRepository.persistAndFlush(byId);
      return EventMapper.INSTANCE.toDto(byId);
    }
  }

  @Transactional
  public void delete(Long id) {
    Optional<EventEntity> byIdOptional = eventRepository.findByIdOptional(id);

    if(eventRepository.hasActiveVoting(id)) {
      throw new RuntimeException("There is an active Voting running.");
    }

    if(!eventRepository.deleteById(id)){
      throw new RuntimeException("Event with id " + id + " not found");
    }
  }

  private static void checkDTOvalues(EventDTO eventDTO) {
    if(isNull(eventDTO.getTitle())|| eventDTO.getTitle().isBlank()){
      throw new RuntimeException("Title must not be null or empty");
    }
    if(eventDTO.getEndDateTimeInUTC() == 0L ||eventDTO.getStartDateTimeInUTC() == 0L){
      throw new RuntimeException("Start and end date must not be null");
    }
    if(eventDTO.getStartDateTimeInUTC()> eventDTO.getEndDateTimeInUTC()){
      throw new RuntimeException("Start date must be before end date");
    }
  }

  private void setValues(EventDTO eventDTOCreate, EventEntity newEntity) {
    newEntity.setTitle(eventDTOCreate.getTitle());
    newEntity.setEndDateTimeInUTC(eventDTOCreate.getEndDateTimeInUTC());
    newEntity.setStartDateTimeInUTC(eventDTOCreate.getStartDateTimeInUTC());
    if(eventDTOCreate.getEventLocation() != null) {
      Optional<EventLocationEntity> locationOptional = this.eventLocationRepository.findByIdOptional(eventDTOCreate.getEventLocation().getEventLocationId());
      if (locationOptional.isEmpty()) {
        throw new RuntimeException("no EventLocation with id " + eventDTOCreate.getEventLocation().getEventLocationId());
      } else {
        newEntity.setEventLocation(locationOptional.get());
      }
    } else {
      throw new RuntimeException("no EventLocation");
    }

    if(eventDTOCreate.getPicture() != null) {
      Optional<PictureEntity> pictureOptional = this.pictureRepository.findByIdOptional(eventDTOCreate.getPicture().getPictureId());
      if (pictureOptional.isEmpty()) {
        throw new RuntimeException("no Picture with id " + eventDTOCreate.getPicture().getPictureId());
      } else {
        newEntity.setPicture(pictureOptional.get());
      }
     }else {
      throw new RuntimeException("no Picture");
    }

    if(eventDTOCreate.getEventInfo() != null) {
      Long eventInfoId = eventDTOCreate.getEventInfo().getEventInfoId();
      if(isNull(eventInfoId)) {
        eventInfoId = 0l;
      }
      Optional<EventInfoEntity> eventInfoOptional = this.eventInfoRepository.findByIdOptional(eventInfoId);
      if (eventInfoOptional.isEmpty()) {
        EventInfoDTO eventInfoDto= this.eventInfoService.create(eventDTOCreate.getEventInfo());
        eventInfoOptional = this.eventInfoRepository.findByIdOptional(eventInfoDto.getEventInfoId());
      }
      newEntity.setEventInfo(eventInfoOptional.get());
    } else {
      throw new RuntimeException("no EventInfo");
    }

    if(eventDTOCreate.getEventCategory() != null) {
      Optional<EventCategoryEntity> eventCategoryOptional = this.eventCategoryRepository.findByIdOptional(eventDTOCreate.getEventCategory().getEventCategoryId());
      if (eventCategoryOptional.isEmpty()) {
        throw new RuntimeException("no EventCategory with id " + eventDTOCreate.getEventCategory().getEventCategoryId());
      } else {
        newEntity.setEventCategory(eventCategoryOptional.get());
      }
    } else {
      throw new RuntimeException("no EventCategory");
    }
  }
}
