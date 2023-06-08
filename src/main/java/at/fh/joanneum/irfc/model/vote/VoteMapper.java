package at.fh.joanneum.irfc.model.vote;

import at.fh.joanneum.irfc.persistence.entiy.VoteEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@Mapper
public interface VoteMapper {
    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    VoteDTO toDto(VoteEntity entity);

    @InheritInverseConfiguration
    VoteEntity toEntity(VoteDTO dto);

    void update(VoteDTO dto, @MappingTarget VoteEntity entity);

    @AfterMapping
    default void mapVotingAndEventIds(VoteEntity entity, @MappingTarget VoteDTO dto) {
        if (entity.getVoting() != null) {
            dto.setVotingId(entity.getVoting().getVotingId());
        }
        if (entity.getEvent() != null) {
            dto.setEventId(entity.getEvent().getEventId());
        }
    }
}