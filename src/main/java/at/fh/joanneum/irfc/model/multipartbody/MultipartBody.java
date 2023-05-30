package at.fh.joanneum.irfc.model.multipartbody;

import at.fh.joanneum.irfc.persistence.enums.FileEndingType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@NoArgsConstructor
@Getter
@Setter
@RegisterForReflection
public class MultipartBody {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    @FormParam("altText")
    @PartType(MediaType.TEXT_PLAIN)
    public String altText;

    @FormParam("fileType")
    @PartType(MediaType.TEXT_PLAIN)
    public FileEndingType fileEndingType;
}
