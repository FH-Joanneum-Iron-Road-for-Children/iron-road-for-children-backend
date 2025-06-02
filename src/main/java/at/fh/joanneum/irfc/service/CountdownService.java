package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.countdown.CountdownDTO;
import at.fh.joanneum.irfc.model.countdown.CountdownMapper;
import at.fh.joanneum.irfc.persistence.entiy.*;
import at.fh.joanneum.irfc.persistence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
public class CountdownService {

    @Inject
    CountdownRepository countdownRepository;

    public CountdownDTO getCountdown() {
        CountdownEntity countdownEntity = countdownRepository.listAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No countdown found in the database"));

        return CountdownMapper.INSTANCE.toDto(countdownEntity);
    }

    @Transactional
    public CountdownDTO update(CountdownDTO countdownDTO) {

        Optional<CountdownEntity> byIdOptional = countdownRepository.listAll().stream()
                .findFirst();
        checkDTOvalues(countdownDTO);

        if (byIdOptional.isEmpty()) {
            CountdownEntity newEntity = new CountdownEntity();
            setValues(countdownDTO, newEntity);
            countdownRepository.persist(newEntity);
            return CountdownMapper.INSTANCE.toDto(newEntity);
        } else {
            CountdownEntity byId = byIdOptional.get();
            setValues(countdownDTO, byId);
            countdownRepository.persistAndFlush(byId);
            return CountdownMapper.INSTANCE.toDto(byId);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!countdownRepository.deleteById(id)) {
            throw new RuntimeException("Countdown with id " + id + " not found");
        }
    }

    private static void checkDTOvalues(CountdownDTO countdownDTO) {
        if (countdownDTO.getEndDateTimeInUTC() == 0L) {
            throw new RuntimeException("End date must not be null");
        }
    }

    private void setValues(CountdownDTO countdownDTOCreate, CountdownEntity newEntity) {
        newEntity.setEndDateTimeInUTC(countdownDTOCreate.getEndDateTimeInUTC());
    }
}
