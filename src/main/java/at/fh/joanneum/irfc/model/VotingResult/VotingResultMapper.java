package at.fh.joanneum.irfc.model.VotingResult;

import at.fh.joanneum.irfc.persistence.entiy.VotingResultEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author https://github.com/GoldNova
 **/
@Mapper
public interface VotingResultMapper {
    VotingResultMapper INSTANCE = Mappers.getMapper(VotingResultMapper.class);

    VotingResultDTO toDto(VotingResultEntity entity);

    @InheritInverseConfiguration
    VotingResultEntity toEntity(VotingResultDTO dto);

    void update(VotingResultDTO dto, @MappingTarget VotingResultEntity entity);

}
