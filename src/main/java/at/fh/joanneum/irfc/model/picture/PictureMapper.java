package at.fh.joanneum.irfc.model.picture;

import at.fh.joanneum.irfc.persistence.entiy.PictureEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author gregor.wakonig@edu.fh-joanneum.at
 **/
@Mapper
public interface PictureMapper {
    PictureMapper INSTANCE = Mappers.getMapper(PictureMapper.class);

    PictureDTO toDto(PictureEntity entity);

    @InheritInverseConfiguration
    PictureEntity toEntity(PictureDTO dto);

    void update(PictureDTO dto, @MappingTarget PictureEntity entity);
}
