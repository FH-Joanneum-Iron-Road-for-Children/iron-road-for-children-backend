package at.fh.joanneum.irfc.model.highlight;

import at.fh.joanneum.irfc.persistence.entiy.HighlightEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author david.krall@edu.fh-joanneum.at
 **/
@Mapper
public interface HighlightMapper {
    HighlightMapper INSTANCE = Mappers.getMapper(HighlightMapper.class);

    HighlightDTO toDto(HighlightEntity entity);

    @InheritInverseConfiguration
    HighlightEntity toEntity(HighlightDTO dto);

    void update(HighlightDTO dto, @MappingTarget HighlightEntity entity);
}
