package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.voting.VotingDTO;
import at.fh.joanneum.irfc.model.voting.VotingMapper;
import at.fh.joanneum.irfc.persistence.repository.VotingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author https://github.com/GoldNova
 **/
@RequestScoped
public class VotingService {
    @Inject
    VotingRepository votingRepository;
    public List<VotingDTO> getAll() {
        return votingRepository.listAll().stream()
                .map(VotingMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
