package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.event.EventMapper;
import at.fh.joanneum.irfc.model.picture.PictureDTO;
import at.fh.joanneum.irfc.model.picture.PictureMapper;
import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.model.voting.VotingMapper;
import at.fh.joanneum.irfc.persistence.entiy.EventCategoryEntity;
import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import at.fh.joanneum.irfc.persistence.repository.EventRepository;
import at.fh.joanneum.irfc.persistence.repository.VotingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingService {
    @Inject
    VotingRepository votingRepository;

    @Inject
    EventRepository eventRepository;

    public List<VotingDTO> getAll() {

        List<VotingEntity> resultSet = votingRepository.listAll();

        List<VotingDTO> collect = resultSet.stream()
                .map(VotingMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
        return collect;
    }

    public VotingDTO get(Long id) {
        VotingEntity votingEntity = votingRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Voting with id " + id + " not found"));

        return VotingMapper.INSTANCE.toDto(votingEntity);
    }

    @Transactional
    public VotingDTO create(VotingDTO votingDTOCreate) {
        validateVoting(votingDTOCreate);

        VotingEntity newEntity = new VotingEntity();
        setValues(votingDTOCreate, newEntity);
        votingRepository.persist(newEntity);
        return VotingMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public VotingDTO update(Long id, VotingDTO votingResultDTO) {
        /// Check for empty vals first
        validateVoting(votingResultDTO);

        // get entity by id
        Optional<VotingEntity> byIdOptional = votingRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("Voting with id " + id + " not found");
        } else {
            VotingEntity byId = byIdOptional.get();
            setValues(votingResultDTO, byId);
            votingRepository.persistAndFlush(byId);
            return VotingMapper.INSTANCE.toDto(byId);
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!votingRepository.deleteById(id)){
            throw new RuntimeException("Voting with id " + id + " not found");
        }
    }

    private void setValues(VotingDTO votingDTOCreate, VotingEntity newEntity) {
        newEntity.setTitle(votingDTOCreate.getTitle());
        newEntity.setActive(votingDTOCreate.isActive());
        newEntity.setEditable(votingDTOCreate.isEditable());

        Set<EventEntity> events;
        if(newEntity.getEvents() != null) {
            events = newEntity.getEvents();
        } else {
            events = new HashSet<>();
        }

        for(EventDTO event : votingDTOCreate.getEvents()) {
            Optional<EventEntity> eventOptional = this.eventRepository.findByIdOptional(event.getEventId());
            if(eventOptional.isEmpty()){
                throw new RuntimeException("no event with id "+ event.getEventId());
            } else {
                EventEntity eventEntity = EventMapper.INSTANCE.toEntity(event);
                events.add(eventEntity);
            }
        }

        newEntity.setEvents(events);

        if(newEntity.isEditable() !=  votingDTOCreate.isEditable() && newEntity.getVotingId() != null) {
            throw new RuntimeException("IsEditable can't be changed");
        }

        if(newEntity.isActive() !=  votingDTOCreate.isActive() && newEntity.getVotingId() != null) {
            throw new RuntimeException("IsActive can't be changed");
        }
        //TODO add events and votingResults
    }

    // Validates the incoming voting DTO
    public static void validateVoting(VotingDTO votingDTO){
        if (isNull(votingDTO.getTitle()) || votingDTO.getTitle().isEmpty()){
            throw new RuntimeException("Title must not be null or empty");
        }


        //TODO: Validate min of 2 events
    }

}
