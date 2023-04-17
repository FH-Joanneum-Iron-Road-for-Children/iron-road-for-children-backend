package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventCategoryDTO;
import at.fh.joanneum.irfc.model.event.EventCategoryMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventCategoryEntity;
import at.fh.joanneum.irfc.persistence.repository.EventCategoryRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@RequestScoped
public class EventCategoryService {

  @Inject
  EventCategoryRepository eventCategoryRepository;
  public List<EventCategoryDTO> getAll() {
    return eventCategoryRepository.listAll().stream()
        .map(EventCategoryMapper.INSTANCE::toDto)
        .collect(Collectors.toUnmodifiableList());
  }

  public EventCategoryDTO get(Long id) {
    Optional<EventCategoryEntity> byIdOptional = eventCategoryRepository.findByIdOptional(id);
    if(byIdOptional.isEmpty()){
      throw new RuntimeException("Event category with id " + id + " not found");
    } else {
      EventCategoryEntity byId = byIdOptional.get();
      return EventCategoryMapper.INSTANCE.toDto(byId);
    }
  }

  @Transactional
  public EventCategoryDTO create(EventCategoryDTO eventCategoryDTO) {

    checkDTOvalues(eventCategoryDTO);

    EventCategoryEntity newEntity = new EventCategoryEntity();
    setValues(eventCategoryDTO, newEntity);
    eventCategoryRepository.persist(newEntity);
    return EventCategoryMapper.INSTANCE.toDto(newEntity);
  }

  @Transactional
  public EventCategoryDTO update(Long id, EventCategoryDTO eventCategoryDTO) {
    Optional<EventCategoryEntity> byIdOptional = eventCategoryRepository.findByIdOptional(id);

    checkDTOvalues(eventCategoryDTO);

    if(byIdOptional.isEmpty()){
      throw new RuntimeException("Event category with id " + id + " not found");
    } else {
      EventCategoryEntity byId = byIdOptional.get();
      setValues(eventCategoryDTO, byId);
      eventCategoryRepository.persistAndFlush(byId);
      return EventCategoryMapper.INSTANCE.toDto(byId);
    }
  }

  @Transactional
  public void delete(Long id) {
    if(!eventCategoryRepository.deleteById(id)){
      throw new RuntimeException("Event category with id " + id + " not found");
    }
  }

  private static void checkDTOvalues(EventCategoryDTO eventCategoryDTO) {
    if(isNull(eventCategoryDTO.getName())|| eventCategoryDTO.getName().isBlank()){
      throw new RuntimeException("Name must not be null or empty");
    }
  }

  private static void setValues(EventCategoryDTO eventCategoryDTO, EventCategoryEntity newEntity) {
    newEntity.setName(eventCategoryDTO.getName());
  }
}
