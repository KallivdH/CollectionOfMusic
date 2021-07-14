package nl.saxion.webapps.collectionofmusic.Image;

import nl.saxion.webapps.collectionofmusic.lp.LPModel;
import nl.saxion.webapps.collectionofmusic.lp.LPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping()
    public List<ImageModel> getAllImages(){
        return imageService.getAllImages();
    }

    @GetMapping("/available")
    public List<ImageModel> getAllAvailableImages(){
        return imageService.getAllAvailableImages();
    }

    @GetMapping("/{id}")
    public ImageModel getImageByID(@PathVariable Long id){
        return imageService.getImageByID(id);
    }

    @PostMapping()
    public void addImage(@RequestParam MultipartFile image, @RequestParam String name) throws IOException {
        imageService.addImage(image, name);
    }

    @PutMapping("/{id}")
    public void updateImage(@PathVariable Long id, @RequestBody ImageModel image){
        imageService.updateImage(id, image);
    }

    @PutMapping()
    public void updateAllImages(@RequestBody List<ImageModel> images){
        imageService.updateAllImages(images);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
    }

    @DeleteMapping()
    public void deleteAllImages(){
        imageService.deleteAllImages();
    }




}
