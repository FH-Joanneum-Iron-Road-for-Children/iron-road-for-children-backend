package at.fh.joanneum.irfc.model.countdown;

import at.fh.joanneum.irfc.persistence.entiy.CountdownEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Kainbacher Dominik
 **/
@Mapper
public interface CountdownMapper {
    CountdownMapper INSTANCE = Mappers.getMapper(CountdownMapper.class);

    CountdownDTO toDto(CountdownEntity entity);

    @InheritInverseConfiguration
    CountdownEntity toEntity(CountdownDTO dto);

    void update(CountdownDTO dto, @MappingTarget CountdownEntity entity);

}
