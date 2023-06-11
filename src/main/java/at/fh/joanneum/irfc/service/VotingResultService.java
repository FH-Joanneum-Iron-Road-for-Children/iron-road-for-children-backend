package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.VotingResult.VotingResultDTO;
import at.fh.joanneum.irfc.model.VotingResult.VotingResultMapper;
import at.fh.joanneum.irfc.persistence.entiy.VotingResultEntity;
import at.fh.joanneum.irfc.persistence.repository.VotingRepository;
import at.fh.joanneum.irfc.persistence.repository.VotingResultRepository;

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
public class VotingResultService {
    @Inject
    VotingResultRepository votingResultRepository;

    @Inject
    VotingRepository votingRepository;
    public List<VotingResultDTO> getAll() {
        return votingResultRepository.listAll().stream()
                .map(VotingResultMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public VotingResultDTO get(Long id) {
        VotingResultEntity votingResultEntity = votingResultRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("VotingResult with id " + id + " not found"));

        return VotingResultMapper.INSTANCE.toDto(votingResultEntity);
    }

    @Transactional
    public VotingResultDTO create(VotingResultDTO votingResultDTOCreate) {
        VotingResultEntity newEntity = new VotingResultEntity();
        setValues(votingResultDTOCreate, newEntity);
        votingResultRepository.persist(newEntity);
        return VotingResultMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public VotingResultDTO update(Long id, VotingResultDTO votingResultDTO) {
        /// Check for empty vals first
        checkDTOvalues(votingResultDTO);

        // get entity by id
        Optional<VotingResultEntity> byIdOptional = votingResultRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new RuntimeException("VotingResult with id " + id + " not found");
        } else {
            VotingResultEntity byId = byIdOptional.get();
            setValues(votingResultDTO, byId);
            votingResultRepository.persistAndFlush(byId);
            return VotingResultMapper.INSTANCE.toDto(byId);
        }
    }

    public static void checkDTOvalues(VotingResultDTO votingResultDTO){
        if (isNull(votingResultDTO.getTitle()) || votingResultDTO.getTitle().isEmpty()){
            throw new RuntimeException("Title must not be null or empty");
        }

        if (isNull(votingResultDTO.getVotingResultId()) || (votingResultDTO.getVotingResultId() < 0L)){
            throw new RuntimeException("Invalid ID");
        }

        if(isNull(votingResultDTO.getEndDate())){
            throw new RuntimeException("End date required");
        }

    }

    @Transactional
    public void delete(Long id) {
        if (!votingResultRepository.deleteById(id)) {
            throw new RuntimeException("VotingResult with id " + id + " not found");
        }
    }

    private static void setValues(VotingResultDTO votingResultDTOCreate, VotingResultEntity newEntity) {
        newEntity.setTitle(votingResultDTOCreate.getTitle());
        newEntity.setEndDate(votingResultDTOCreate.getEndDate());
    }

}
