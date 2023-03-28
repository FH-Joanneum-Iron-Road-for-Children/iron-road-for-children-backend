package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.EventLocationEntity;
import at.fh.joanneum.irfc.persistence.repository.EventLocationRepository;
import at.fh.joanneum.irfc.persistence.repository.EventRepository;

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

  public List<EventDTO> getAll() {
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
  public EventDTO update(Long id, EventDTO eventDTOUpdate) {
    Optional<EventEntity> byIdOptional = eventRepository.findByIdOptional(id);

    checkDTOvalues(eventDTOUpdate);

    if(byIdOptional.isEmpty()){
      throw new RuntimeException("Event with id " + id + " not found");
    } else {
      EventEntity byId = byIdOptional.get();
      setValues(eventDTOUpdate, byId);
      eventRepository.persistAndFlush(byId);
      return EventMapper.INSTANCE.toDto(byId);
    }
  }

  @Transactional
  public void delete(Long id) {
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
    if(isNull(eventDTO.getEventLocation())){
      throw new RuntimeException("EventLocation must not be null");
    }
  }

  private void setValues(EventDTO eventDTOCreate, EventEntity newEntity) {
    newEntity.setTitle(eventDTOCreate.getTitle());
    newEntity.setEndDateTimeInUTC(eventDTOCreate.getEndDateTimeInUTC());
    newEntity.setStartDateTimeInUTC(eventDTOCreate.getStartDateTimeInUTC());
    Optional<EventLocationEntity> locationOptional = this.eventLocationRepository.findByIdOptional(eventDTOCreate.getEventLocation().getEventLocationId());
    if(locationOptional.isEmpty()){
      throw new RuntimeException("no EventLocation with id "+ eventDTOCreate.getEventLocation().getEventLocationId());
    } else {
      newEntity.setEventLocation(locationOptional.get());
    }
  }


}
