package at.fh.joanneum.irfc.model.event;

import at.fh.joanneum.irfc.persistence.entiy.EventCategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author dominik.jaeger@edu.fh-joanneum.at
 **/
@Mapper
public interface EventCategoryMapper {
  EventCategoryMapper INSTANCE = Mappers.getMapper(EventCategoryMapper.class);

  EventCategoryDTO toDto(EventCategoryEntity entity);

  @InheritInverseConfiguration
  EventCategoryEntity toEntity(EventCategoryDTO dto);

  void update(EventCategoryDTO dto, @MappingTarget EventCategoryEntity entity);
}
