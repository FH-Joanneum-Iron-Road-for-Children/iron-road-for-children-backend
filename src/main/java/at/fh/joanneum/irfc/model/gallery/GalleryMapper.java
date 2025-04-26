package at.fh.joanneum.irfc.model.gallery;

import at.fh.joanneum.irfc.persistence.entiy.GalleryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author dominik.kainbacher@edu.fh-joanneum.at
 **/
@Mapper
public interface GalleryMapper {
    GalleryMapper INSTANCE = Mappers.getMapper(GalleryMapper.class);

    GalleryDTO toDto(GalleryEntity entity);

    @InheritInverseConfiguration
    GalleryEntity toEntity(GalleryDTO dto);

    void update(GalleryDTO dto, @MappingTarget GalleryEntity entity);
}