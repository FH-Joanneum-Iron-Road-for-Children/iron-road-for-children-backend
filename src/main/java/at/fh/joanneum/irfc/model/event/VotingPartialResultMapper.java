package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import at.fh.joanneum.irfc.persistence.entiy.VotingPartialResultEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author https://github.com/GoldNova
 **/
@Mapper
public interface VotingPartialResultMapper {
    VotingPartialResultMapper INSTANCE = Mappers.getMapper(VotingPartialResultMapper.class);

    VotingPartialResultDTO toDto(VotingPartialResultEntity entity);

    @InheritInverseConfiguration
    VotingPartialResultEntity toEntity(VotingPartialResultDTO dto);

    void update(VotingPartialResultDTO dto, @MappingTarget VotingPartialResultEntity entity);

}
