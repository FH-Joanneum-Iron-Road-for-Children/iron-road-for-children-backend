package at.fh.joanneum.irfc.model.playlist;

import at.fh.joanneum.irfc.persistence.entiy.PlaylistEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author max pfisterer
 **/
@Mapper
public interface PlaylistMapper {
    PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);

    PlaylistDTO toDto(PlaylistEntity entity);

    @InheritInverseConfiguration
    PlaylistEntity toEntity(PlaylistDTO dto);

    void update(PlaylistDTO dto, @MappingTarget PlaylistEntity entity);

}
