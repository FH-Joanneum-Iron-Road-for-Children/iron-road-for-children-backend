package at.fh.joanneum.irfc.model.voting;

import at.fh.joanneum.irfc.persistence.entiy.VotingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author https://github.com/GoldNova
 **/
@Mapper
public interface VotingMapper {
    VotingMapper INSTANCE = Mappers.getMapper(VotingMapper.class);

    VotingDTO toDto(VotingEntity entity);

    @InheritInverseConfiguration
    VotingEntity toEntity(VotingDTO dto);

    void update(VotingDTO dto, @MappingTarget VotingEntity entity);

}