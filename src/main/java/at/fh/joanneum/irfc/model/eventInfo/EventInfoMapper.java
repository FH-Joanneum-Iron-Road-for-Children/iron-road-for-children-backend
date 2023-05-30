package at.fh.joanneum.irfc.model.eventInfo;

import at.fh.joanneum.irfc.persistence.entiy.EventInfoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@Mapper
public interface EventInfoMapper {
    EventInfoMapper INSTANCE = Mappers.getMapper(EventInfoMapper.class);

    EventInfoDTO toDto(EventInfoEntity entity);

    @InheritInverseConfiguration
    EventInfoEntity toEntity(EventInfoDTO dto);

    void update(EventInfoDTO dto, @MappingTarget EventInfoEntity entity);

}