package nl.saxion.webapps.collectionofmusic.artist;

import nl.saxion.webapps.collectionofmusic.Image.ImageRepository;
import nl.saxion.webapps.collectionofmusic.lp.LPModel;
import nl.saxion.webapps.collectionofmusic.lp.LPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ImageRepository imageRepository) {
        this.artistRepository = artistRepository;
        this.imageRepository = imageRepository;
    }

    public List<ArtistModel> getAllArtists() { return artistRepository.findAll(); }

    public ArtistModel getArtistByID(Long id){
        Optional<ArtistModel> c = artistRepository.findById(id);
        return c.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    public void addArtist(ArtistModel artist, Long imgId) {
        artist.setImage(imageRepository.getById(imgId));
        artistRepository.save(artist);
    }

    public void updateArtist(Long id, ArtistModel artist, Long imgId) {

        if(artistRepository.findById(id).isPresent()) {
            artist.setImage(imageRepository.getById(imgId));
            artistRepository.save(artist);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public void updateAllArtist(List<ArtistModel> artists) {
        artistRepository.saveAll(artists);
    }

    public void deleteArtist(Long id) {
        if(artistRepository.findById(id).isPresent()) {
            artistRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public Resource getArtistImg(Long id) {
        Optional<ArtistModel> c = artistRepository.findById(id);
        c.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        byte[] image = c.get().getImage().getContent();
        return new ByteArrayResource(image);
    }

    public void deleteAllArtists() {
        artistRepository.deleteAll();
    }
}
