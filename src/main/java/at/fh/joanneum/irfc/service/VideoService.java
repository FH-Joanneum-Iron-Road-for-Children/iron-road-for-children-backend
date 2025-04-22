package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.multipartbody.MultipartBody;
import at.fh.joanneum.irfc.model.video.VideoDTO;
import at.fh.joanneum.irfc.model.video.VideoMapper;
import at.fh.joanneum.irfc.persistence.entiy.VideoEntity;
import at.fh.joanneum.irfc.persistence.enums.FileEndingType;
import at.fh.joanneum.irfc.persistence.repository.VideoRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author Max Pfisterer
 **/
@RequestScoped
public class VideoService {
    @Inject
    VideoRepository videoRepository;

    @ConfigProperty(name = "videos.root_path")
    String videoRootPath;

    @ConfigProperty(name = "videos.url")
    String videoUrl;

    @ConfigProperty(name = "videos.intro_video_folder")
    String introVideoFolder;

    @ConfigProperty(name = "videos.intro_video_file")
    String introVideoFile;


    public List<VideoDTO> getAll() {
        return videoRepository.listAll().stream()
                .map(VideoMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public VideoDTO get(Long id) {
        Optional<VideoEntity> byIdOptional = videoRepository.findByIdOptional(id);
        if (byIdOptional.isEmpty()) {
            throw new RuntimeException("Video with id " + id + " not found");
        } else {
            VideoEntity byId = byIdOptional.get();
            return VideoMapper.INSTANCE.toDto(byId);
        }
    }

    @Transactional
    public void delete(Long id) {
        var videoDTO = get(id);
        try {
            File f = new File(videoDTO.getPath().replace(videoUrl, videoRootPath));
            if (!f.delete()) {
                throw new RuntimeException("Video with Path " + videoDTO.getPath() + " could not be deleted");
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong deleting picture with path " + videoDTO.getPath() + ": " + e.getMessage());
        }
        if (!videoRepository.deleteById(id)) {
            throw new RuntimeException("Video with id " + id + " not found");
        }
    }

    /*
     * Create a new Video
     * @param data the video data
     * @param List of folder names without slashes, sub-folders will be created if they do not exist
     * @param videoName without file ending, if null a random UUID will be used
     */
    @Transactional
    public VideoDTO create(MultipartBody data, List<String> subfolders, String videoName) {
        VideoDTO videoDTO = new VideoDTO();

        String altText = data.getAltText();
        if (isNull(altText) || altText.isBlank()) {
            throw new RuntimeException("Alt-Text must be provided");
        }
        videoDTO.setAltText(altText);

        // use a random UUID if no name is provided
        if (videoName == null) {
            videoName = UUID.randomUUID().toString();
        }
        String subfolderPath = createSubfolderPath(subfolders);

        try {
            String fileName = subfolderPath + videoName + "." + data.getFileEndingType().name().toLowerCase();
            File targetFile = new File(videoRootPath + fileName);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(data.file.readAllBytes());
            outStream.close();
            videoDTO.setPath(videoUrl + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error storing Video");
        }

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setAltText(videoDTO.getAltText());
        videoEntity.setPath(videoDTO.getPath());

        videoRepository.persist(videoEntity);
        return VideoMapper.INSTANCE.toDto(videoEntity);
    }

    public Optional<VideoDTO> getIntroVideo() {
        return videoRepository.findByPathOptional(getIntroVideoPath())
                .map(VideoMapper.INSTANCE::toDto);
    }
    @Transactional
    public void deleteIntroVideo() {
        // get intro video id by path
        Optional<VideoEntity> introVideo = videoRepository.findByPathOptional(getIntroVideoPath());
        try {
            introVideo.ifPresent(videoEntity -> delete(videoEntity.getVideoId()));
        } catch (Exception e) {
            throw new RuntimeException("Error deleting intro video: " + e.getMessage());
        }
    }

    @Transactional
    public VideoDTO createIntroVideo(MultipartBody data) {
        FileEndingType fileEndingType = data.getFileEndingType();
        if (fileEndingType != FileEndingType.MP4) {
            throw new RuntimeException("Intro Video must be of type MP4");
        }
        deleteIntroVideo();
        return create(data, List.of(introVideoFolder), introVideoFile);
    }


    private String getIntroVideoPath() {
        return videoUrl + introVideoFolder + "/" + introVideoFile + ".mp4";
    }

    private String createSubfolderPath(List<String> subfolders) {
        String subfolderPath = String.join("/", subfolders);
        if (!subfolderPath.isEmpty()) {
            File fullPath = new File(videoRootPath, subfolderPath);
            if (!fullPath.exists() && !fullPath.mkdirs()) {
                throw new RuntimeException("Error creating subfolder path: " + fullPath.getPath());
            }
            subfolderPath += "/";
        }
        return subfolderPath;
    }

}
