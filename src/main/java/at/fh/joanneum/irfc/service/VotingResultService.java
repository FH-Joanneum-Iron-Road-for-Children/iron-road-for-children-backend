package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.VotingResult.VotingResultDTO;
import at.fh.joanneum.irfc.model.VotingResult.VotingResultMapper;
import at.fh.joanneum.irfc.persistence.entiy.VotingResultEntity;
import at.fh.joanneum.irfc.persistence.repository.VotingResultRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author https://github.com/GoldNova
 **/

@RequestScoped
public class VotingResultService {

    @Inject
    VotingResultRepository votingResultRepository;
    public List<VotingResultDTO> getAll() {
        return votingResultRepository.listAll().stream()
                .map(VotingResultMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public VotingResultDTO create(VotingResultDTO votingResultDTOCreate) {
        VotingResultEntity newEntity = new VotingResultEntity();
        setValues(votingResultDTOCreate, newEntity);
        votingResultRepository.persist(newEntity);
        return VotingResultMapper.INSTANCE.toDto(newEntity);
    }
//
//    @Transactional
//    public EventDTO update(Long id, EventDTO eventDTOUpdate) {
//        Optional<EventEntity> byIdOptional = eventRepository.findByIdOptional(id);
//
//        if(byIdOptional.isEmpty()){
//            throw new RuntimeException("Event with id " + id + " not found");
//        } else {
//            EventEntity byId = byIdOptional.get();
//            setValues(eventDTOUpdate, byId);
//            eventRepository.persistAndFlush(byId);
//            return EventMapper.INSTANCE.toDto(byId);
//        }
//    }
//
    private static void setValues(VotingResultDTO votingResultDTOCreate, VotingResultEntity newEntity) {
        newEntity.setTitle(votingResultDTOCreate.getTitle());
        newEntity.setEndDate(votingResultDTOCreate.getEndDate());
//       newEntity.setpartialResults(votingResultDTOCreate.getpartialResults());
    }
//
//    @Transactional
//    public void delete(Long id) {
//        if(!eventRepository.deleteById(id)){
//            throw new RuntimeException("Event with id " + id + " not found");
//        }
//
//    }
}
