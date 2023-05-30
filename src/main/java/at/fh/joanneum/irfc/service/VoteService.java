package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.vote.VoteDTO;
import at.fh.joanneum.irfc.model.vote.VoteMapper;
import at.fh.joanneum.irfc.persistence.entiy.VoteEntity;
import at.fh.joanneum.irfc.persistence.repository.VoteRepository;

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
public class VoteService {

    @Inject
    VoteRepository voteRepository;


    public List<VoteDTO> getAll() {
        return voteRepository.listAll().stream()
                .map(VoteMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public VoteDTO getById(Long id) {
        VoteEntity byId = voteRepository.findById(id);
        return VoteMapper.INSTANCE.toDto(byId);
    }

    @Transactional
    public VoteDTO create(VoteDTO voteDTO) {
        validateDTOvalues(voteDTO);
        VoteEntity newEntity = new VoteEntity();
        setValues(voteDTO, newEntity);
        voteRepository.persist(newEntity);
        return VoteMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public VoteDTO update(Long id, VoteDTO voteDTO) {
        Optional<VoteEntity> byIdOptional = voteRepository.findByIdOptional(id);

        validateDTOvalues(voteDTO);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("Voting with id " + id + " not found");
        } else {
            VoteEntity byId = byIdOptional.get();
            setValues(voteDTO, byId);
            voteRepository.persistAndFlush(byId);
            return VoteMapper.INSTANCE.toDto(byId);
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!voteRepository.deleteById(id)){
            throw new RuntimeException("Voting with id " + id + " not found");
        }
    }

    private static void validateDTOvalues(VoteDTO voteDTO) {
        if(isNull(voteDTO.getVotingId())){
            throw new RuntimeException("Voting ID must not be null");
        }
        if(isNull(voteDTO.getEventId())){
            throw new RuntimeException("Event ID must not be null");
        }
        if(isNull(voteDTO.getDeviceId())){
            throw new RuntimeException("Device ID must not be null");
        }
    }

    private void setValues(VoteDTO voteDTO, VoteEntity newEntity) {
        newEntity.setVotingId(voteDTO.getVotingId());
        newEntity.setEventId(voteDTO.getEventId());
        newEntity.setDeviceId(voteDTO.getDeviceId());
    }
}