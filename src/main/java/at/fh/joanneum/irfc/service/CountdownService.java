package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.countdown.CountdownDTO;
import at.fh.joanneum.irfc.model.countdown.CountdownMapper;
import at.fh.joanneum.irfc.persistence.entiy.*;
import at.fh.joanneum.irfc.persistence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author Kainbacher Dominik
 **/
@RequestScoped
public class CountdownService {

    @Inject
    CountdownRepository countdownRepository;



    public List<CountdownDTO> getAll() { //TODO throws exception (pls fix)
        List<CountdownEntity> all = countdownRepository.listAll();
        List<CountdownDTO> allMapped = all.stream()
                .map(CountdownMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
        return allMapped;
    }

    public CountdownDTO get(Long id) {
        CountdownEntity countdownEntity = countdownRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Countdown with id " + id + " not found"));

        return CountdownMapper.INSTANCE.toDto(countdownEntity);
    }

    @Transactional
    public CountdownDTO create(CountdownDTO countdownDTO) {
        checkDTOvalues(countdownDTO);

        CountdownEntity newEntity = new CountdownEntity();
        setValues(countdownDTO, newEntity);
        countdownRepository.persist(newEntity);
        return CountdownMapper.INSTANCE.toDto(newEntity);
    }

    @Transactional
    public CountdownDTO update(Long id, CountdownDTO countdownDTO) {

        Optional<CountdownEntity> byIdOptional = countdownRepository.findByIdOptional(id);

        checkDTOvalues(countdownDTO);

        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Countdown with id " + id + " not found");
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
        if (countdownDTO.getEndDateTimeInUTC() == 0L ) {
            throw new RuntimeException("End date must not be null");
        }
    }

    private void setValues(CountdownDTO countdownDTOCreate, CountdownEntity newEntity) {
        newEntity.setEndDateTimeInUTC(countdownDTOCreate.getEndDateTimeInUTC());
    }
}
