package at.fh.joanneum.irfc.model.eventlocation;

import at.fh.joanneum.irfc.persistence.entiy.EventLocationEntiy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author moe@softwaregaertner.at
 **/
@Mapper
public interface EventLocationMapper {

    EventLocationMapper INSTANCE = Mappers.getMapper(EventLocationMapper.class);

    EventLocationDTO toDto(EventLocationEntiy entity);

    @InheritInverseConfiguration
    EventLocationEntiy toEntity(EventLocationDTO dto);

    void update(EventLocationDTO dto, @MappingTarget EventLocationEntiy entity);

}
