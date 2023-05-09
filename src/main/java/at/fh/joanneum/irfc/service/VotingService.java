package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.model.voting.VotingMapper;
import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import at.fh.joanneum.irfc.persistence.repository.VotingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingService {
    @Inject
    VotingRepository votingRepository;

    public List<VotingDTO> getAll() {

        List<VotingEntity> resultSet = votingRepository.listAll();

        List<VotingDTO> collect = resultSet.stream()
                .map(VotingMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
        return collect;
    }

    public VotingDTO get(Long id) {
        VotingEntity eventEntity = votingRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Voting with id " + id + " not found"));

        return VotingMapper.INSTANCE.toDto(eventEntity);
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

    private static void setValues(VotingDTO votingDTOCreate, VotingEntity newEntity) {
        newEntity.setTitle(votingDTOCreate.getTitle());
        newEntity.setActive(votingDTOCreate.isActive());
        newEntity.setEditable(votingDTOCreate.isEditable());

    }

    // Validates the incoming voting DTO
    public static void validateVoting(VotingDTO votingDTO){
        if (isNull(votingDTO.getTitle()) || votingDTO.getTitle().isEmpty()){
            throw new RuntimeException("Title must not be null or empty");
        }

        if (isNull(votingDTO.isActive())){
            throw new RuntimeException("isActive attribute missing");
        }

        if(isNull(votingDTO.isEditable())){
            throw new RuntimeException("isEditable attribute missing");
        }

        //TODO: Validate rest of the fields after merge
    }

}
