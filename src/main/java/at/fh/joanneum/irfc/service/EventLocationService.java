package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.eventlocation.EventLocationDTO;
import at.fh.joanneum.irfc.model.eventlocation.EventLocationMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventLocationEntity;
import at.fh.joanneum.irfc.persistence.repository.EventLocationRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class EventLocationService {

    @Inject
    EventLocationRepository eventLocationRepository;

    public List<EventLocationDTO> getAll() {
        return eventLocationRepository.listAll().stream()
                .map(EventLocationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }


    public EventLocationDTO getById(Long id) {
        EventLocationEntity byId = eventLocationRepository.findById(id);
        return EventLocationMapper.INSTANCE.toDto(byId);
    }

    @Transactional
    public EventLocationDTO create(EventLocationDTO eventLocationDTOcreate) {
        EventLocationEntity newEntity = new EventLocationEntity();
        setValues(eventLocationDTOcreate, newEntity);
        eventLocationRepository.persist(newEntity);
        return EventLocationMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public EventLocationDTO update(Long id, EventLocationDTO eventLocationDTOUpdate) {
        Optional<EventLocationEntity> byIdOptional = eventLocationRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventLocation with id " + id + " not found");
        } else {
            EventLocationEntity eventLocationEntity = byIdOptional.get();
            setValues(eventLocationDTOUpdate, eventLocationEntity);
            eventLocationRepository.persistAndFlush(eventLocationEntity);
            return EventLocationMapper.INSTANCE.toDto(eventLocationEntity);
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!eventLocationRepository.deleteById(id)){
            throw new RuntimeException("EventLocation with id " + id + " not found");
        }
    }

    private static void setValues(EventLocationDTO eventLocationDTOcreate, EventLocationEntity newEntity) {
        newEntity.setName(eventLocationDTOcreate.getName());
    }


}
