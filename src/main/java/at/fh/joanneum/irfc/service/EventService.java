package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.repository.EventRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
public class EventService {

  @Inject
  EventRepository eventRepository;
  public List<EventDTO> getAll() {
    return eventRepository.listAll().stream()
        .map(EventMapper.INSTANCE::toDto)
        .collect(Collectors.toUnmodifiableList());
  }

  @Transactional
  public EventDTO create(EventDTO eventDTOCreate) {
    EventEntity newEntity = new EventEntity();
    setValues(eventDTOCreate, newEntity);
    eventRepository.persist(newEntity);
    return EventMapper.INSTANCE.toDto(newEntity);
  }

  @Transactional
  public EventDTO update(Long id, EventDTO eventDTOUpdate) {
    Optional<EventEntity> byIdOptional = eventRepository.findByIdOptional(id);

    if(byIdOptional.isEmpty()){
      throw new RuntimeException("Event with id " + id + " not found");
    } else {
      EventEntity byId = byIdOptional.get();
      setValues(eventDTOUpdate, byId);
      eventRepository.persistAndFlush(byId);
      return EventMapper.INSTANCE.toDto(byId);
    }
  }

  private static void setValues(EventDTO eventDTOCreate, EventEntity newEntity) {
    newEntity.setTitle(eventDTOCreate.getTitle());
    newEntity.setEndDateTimeInUTC(eventDTOCreate.getEndDateTimeInUTC());
    newEntity.setStartDateTimeInUTC(eventDTOCreate.getStartDateTimeInUTC());
  }

  @Transactional
  public void delete(Long id) {
    if(!eventRepository.deleteById(id)){
      throw new RuntimeException("Event with id " + id + " not found");
    }

  }
}
