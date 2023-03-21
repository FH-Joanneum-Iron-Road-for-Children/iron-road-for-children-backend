package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.persistence.entiy.EventEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author moe@softwaregaertner.at
 **/
@Mapper
public interface EventMapper {
  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  EventDTO toDto(EventEntity entity);

  @InheritInverseConfiguration
  EventEntity toEntity(EventDTO dto);

  void update(EventDTO dto, @MappingTarget EventEntity entity);

}
