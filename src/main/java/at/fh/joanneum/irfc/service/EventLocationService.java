package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.eventlocation.EventLocationDTO;
import at.fh.joanneum.irfc.model.eventlocation.EventLocationMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventLocationEntiy;
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
        EventLocationEntiy byId = eventLocationRepository.findById(id);
        return EventLocationMapper.INSTANCE.toDto(byId);
    }

    @Transactional
    public EventLocationDTO create(EventLocationDTO eventLocationDTOcreate) {
        EventLocationEntiy newEntity = new EventLocationEntiy();
        setValues(eventLocationDTOcreate, newEntity);
        eventLocationRepository.persist(newEntity);
        return EventLocationMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public EventLocationDTO update(Long id, EventLocationDTO eventLocationDTOUpdate) {
        Optional<EventLocationEntiy> byIdOptional = eventLocationRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("EventLocation with id " + id + " not found");
        } else {
            EventLocationEntiy eventLocationEntiy = byIdOptional.get();
            setValues(eventLocationDTOUpdate, eventLocationEntiy);
            eventLocationRepository.persistAndFlush(eventLocationEntiy);
            return EventLocationMapper.INSTANCE.toDto(eventLocationEntiy);
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!eventLocationRepository.deleteById(id)){
            throw new RuntimeException("EventLocation with id " + id + " not found");
        }
    }

    private static void setValues(EventLocationDTO eventLocationDTOcreate, EventLocationEntiy newEntity) {
        newEntity.setName(eventLocationDTOcreate.getName());
    }


}
