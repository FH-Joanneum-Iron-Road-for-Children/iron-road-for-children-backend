package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.event.VotingPartialResultDTO;
import at.fh.joanneum.irfc.model.event.VotingPartialResultMapper;
import at.fh.joanneum.irfc.persistence.entiy.VotingPartialResultEntity;
import at.fh.joanneum.irfc.persistence.repository.VotingPartialResultRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingPartialResultService {
    @Inject
    VotingPartialResultRepository votingPartialResultRepository;
    
    @Transactional
    public VotingPartialResultDTO create(VotingPartialResultDTO votingPartialResultDTOCreate) {
        VotingPartialResultEntity newEntity = new VotingPartialResultEntity();
        setValues(votingPartialResultDTOCreate, newEntity);
        votingPartialResultRepository.persist(newEntity);
        return VotingPartialResultMapper.INSTANCE.toDto(newEntity);
    }
    
    private static void setValues(VotingPartialResultDTO votingPartialResultDTOCreate, VotingPartialResultEntity newEntity) {
        newEntity.setEventName(votingPartialResultDTOCreate.getEventName());
        newEntity.setPercentage(votingPartialResultDTOCreate.getPercentage());
    }
}
