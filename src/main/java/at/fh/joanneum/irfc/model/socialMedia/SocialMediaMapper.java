package at.fh.joanneum.irfc.model.socialMedia;

import at.fh.joanneum.irfc.persistence.entiy.SocialMediaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Kainbacher Dominik
 **/
@Mapper
public interface SocialMediaMapper {
    SocialMediaMapper INSTANCE = Mappers.getMapper(SocialMediaMapper.class);

    SocialMediaDTO toDto(SocialMediaEntity entity);

    @InheritInverseConfiguration
    SocialMediaEntity toEntity(SocialMediaDTO dto);

    void update(SocialMediaDTO dto, @MappingTarget SocialMediaEntity entity);

}
