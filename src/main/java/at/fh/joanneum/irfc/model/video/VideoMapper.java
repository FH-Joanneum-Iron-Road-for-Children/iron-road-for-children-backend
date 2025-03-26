package at.fh.joanneum.irfc.model.video;

import at.fh.joanneum.irfc.persistence.entiy.VideoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Max Pfisterer
 **/
@Mapper
public interface VideoMapper {
     VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    VideoDTO toDto(VideoEntity entity);

    @InheritInverseConfiguration
    VideoEntity toEntity(VideoDTO dto);

    void update(VideoDTO dto, @MappingTarget VideoEntity entity);
}
