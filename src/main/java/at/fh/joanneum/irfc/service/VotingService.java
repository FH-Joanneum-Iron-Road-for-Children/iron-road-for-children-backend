package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.EventDTO;
import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.model.voting.VotingMapper;
import at.fh.joanneum.irfc.persistence.entiy.*;
import at.fh.joanneum.irfc.persistence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;
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

    @Inject
    VoteRepository voteRepository;

    @Inject
    VotingPartialResultRepository votingPartialResultRepository;

    @Inject
    VotingResultRepository votingResultRepository;

    public List<VotingDTO> getAll() {

        List<VotingEntity> resultSet = votingRepository.listAll();

        List<VotingDTO> collect = resultSet.stream().map(VotingMapper.INSTANCE::toDto).collect(Collectors.toUnmodifiableList());
        return collect;
    }

    public VotingDTO get(Long id) {
        VotingEntity votingEntity = votingRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("Voting with id " + id + " not found"));

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
        if (!votingRepository.isVotingEditable(id)) {
            throw new RuntimeException("Can't update a voting which is Active");
        }
        /// Check for empty vals first
        validateVoting(votingResultDTO);

        // get entity by id
        Optional<VotingEntity> byIdOptional = votingRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()) {
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
        if (votingRepository.isVotingActive(id)) {
            throw new RuntimeException("Please stop the voting before you delete it");
        }

        if (!votingRepository.deleteById(id)) {
            throw new RuntimeException("Voting with id " + id + " not found");
        }
    }

    @Transactional
    public VotingDTO startVoting(Long id) {
        Optional<VotingEntity> byIdOptional = votingRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Voting with id " + id + " not found");
        } else {
            VotingEntity byId = byIdOptional.get();
            if (byId.isActive() == true || byId.isEditable() == false) {
                throw new RuntimeException("Voting can't be started because it's either already started or has already been endet");
            }
            byId.setActive(true);
            byId.setEditable(false);
        }

        return VotingMapper.INSTANCE.toDto(byIdOptional.get());
    }

    @Transactional
    public VotingDTO endVoting(Long id) {
        Optional<VotingEntity> byIdOptional = votingRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Voting with id " + id + " not found");
        } else {
            VotingEntity byId = byIdOptional.get();
            if (byId.isActive() == false) {
                throw new RuntimeException("Voting is not Active");
            }
            byId.setActive(false);
            VotingResultEntity votingResult = new VotingResultEntity();
            votingResult.setTitle(byId.getTitle());
            votingResult.setVoting(byId);
            votingResult.setEndDate(Instant.now().toEpochMilli()); //TODO check if this is correct
            votingResultRepository.persist(votingResult);

            Set<VotingPartialResultEntity> partialResults = new HashSet<>();
            double numOfVotes = this.voteRepository.countVotesByVoting(id);
            for (EventEntity event : byId.getEvents()) {
                long numOfVotesForEvent = this.voteRepository.countVotesByEventAndVoting(event.getEventId(), id);
                VotingPartialResultEntity partialResult = new VotingPartialResultEntity();
                partialResult.setEventName(event.getTitle());
                partialResult.setPercentage(numOfVotes != 0 ? (numOfVotesForEvent / numOfVotes * 1.0) : 0);
                partialResult.setVotingResult(votingResult);
                partialResults.add(partialResult);

                votingPartialResultRepository.persist(partialResult);
            }
            votingResult.setPartialResults(partialResults);


            byId.setVotingResult(votingResult);
        }

        return VotingMapper.INSTANCE.toDto(byIdOptional.get());
    }

    private void setValues(VotingDTO votingDTOCreate, VotingEntity newEntity) {
        newEntity.setTitle(votingDTOCreate.getTitle());

        Set<EventEntity> events = newEntity.getEvents();
        if (events == null) {
            events = new HashSet<>();
        }

        Set<Long> dtoEventIds = new HashSet<>();
        for (EventDTO event : votingDTOCreate.getEvents()) {
            dtoEventIds.add(event.getEventId());
        }

        Iterator<EventEntity> iterator = events.iterator();
        while (iterator.hasNext()) {
            EventEntity eventEntity = iterator.next();
            Long eventId = eventEntity.getEventId();
            if (!dtoEventIds.contains(eventId)) {
                iterator.remove();
            }
        }

        for (EventDTO event : votingDTOCreate.getEvents()) {
            if (!this.eventRepository.isEventIdInList(events, event.getEventId())) {
                Optional<EventEntity> eventOptional = this.eventRepository.findByIdOptional(event.getEventId());
                if (eventOptional.isEmpty()) {
                    throw new RuntimeException("No Event with id " + event.getEventId());
                } else {
                    EventEntity eventEntity = eventOptional.get();
                    events.add(eventEntity);
                }
            }
        }

        newEntity.setEvents(events);

        if (newEntity.isEditable() != votingDTOCreate.isEditable() && newEntity.getVotingId() != null) {
            throw new RuntimeException("IsEditable can't be changed");
        }

        if (newEntity.isActive() != votingDTOCreate.isActive() && newEntity.getVotingId() != null) {
            throw new RuntimeException("IsActive can't be changed");
        }

        //TODO this is a quick hack cause default values doesn't seem to work (pls fix)
        if (newEntity.getVotingId() == null) {
            newEntity.setEditable(true);
            newEntity.setActive(false);
        }
    }

    public static void validateVoting(VotingDTO votingDTO) {
        if (isNull(votingDTO.getTitle()) || votingDTO.getTitle().isEmpty()) {
            throw new RuntimeException("Title must not be null or empty");
        }

        if (votingDTO.getEvents().size() < 2) {
            throw new RuntimeException("There has to be at least to events for a voting");
        }
    }

}
