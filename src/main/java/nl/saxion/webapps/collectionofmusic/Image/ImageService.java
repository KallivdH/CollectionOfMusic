package nl.saxion.webapps.collectionofmusic.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageModel getImageByID(Long id){
        Optional<ImageModel> imageModel = imageRepository.findById(id);
        return imageModel.orElse(null);
    }

    public void addImage(MultipartFile image, String name) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(name);
        imageModel.setContent(image.getBytes());
        imageRepository.save(imageModel);
    }

    public List<ImageModel> getAllImages() {
        return imageRepository.findAll();
    }

    public void updateImage(Long id, ImageModel image) {
        if(imageRepository.findById(id).isPresent()) {
            imageRepository.save(image);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public void updateAllImages(List<ImageModel> images) {
        imageRepository.saveAll(images);
    }

    public void deleteImage(Long id) {
        if(imageRepository.findById(id).isPresent()) {
            imageRepository.delete(imageRepository.findById(id).get());
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public void deleteAllImages() {
        imageRepository.deleteAll();
    }

    public List<ImageModel> getAllAvailableImages() {
        List<ImageModel> imageModels = imageRepository.findAll();
        imageModels.removeIf(imageModel -> imageModel.getLpModel() != null || imageModel.getArtistModel() != null);
        return imageModels;
    }
}
