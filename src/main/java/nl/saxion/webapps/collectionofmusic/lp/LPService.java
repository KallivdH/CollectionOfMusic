package nl.saxion.webapps.collectionofmusic.lp;

import nl.saxion.webapps.collectionofmusic.Image.ImageRepository;
import nl.saxion.webapps.collectionofmusic.artist.ArtistModel;
import nl.saxion.webapps.collectionofmusic.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LPService {

    private final LPRepository lpRepository;
    private final ImageRepository imageRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public LPService(LPRepository lpRepository, ImageRepository imageRepository, ArtistRepository artistRepository) {
        this.lpRepository = lpRepository;
        this.imageRepository = imageRepository;
        this.artistRepository = artistRepository;
    }

    public List<LPModel> getAllLPs() { return lpRepository.findAll(); }

    public LPModel getLPByID(Long id){
        Optional<LPModel> c = lpRepository.findById(id);
        return c.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    public void addLP(LPModel lp, Long imgId, Long artistId) {
        lp.setImage(imageRepository.getById(imgId));
        lp.setArtist(artistRepository.getById(artistId));
        lpRepository.save(lp);
    }

    public void updateLP(Long id, LPModel lp, Long imgId, Long artistId) {
        if(lpRepository.findById(id).isPresent()) {
            lp.setImage(imageRepository.getById(imgId));
            lp.setArtist(artistRepository.getById(artistId));
            lpRepository.save(lp);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public void deleteLP(Long id) {
        if(lpRepository.findById(id).isPresent()) {
            lpRepository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public void updateLPs(List<LPModel> lps) {
        lpRepository.saveAll(lps);
    }

    public void deleteAllLPs() {
        lpRepository.deleteAll();
    }

    public Resource getLPImage(Long id) {
        Optional<LPModel> c = lpRepository.findById(id);
        c.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        byte[] image = c.get().getImage().getContent();
        return new ByteArrayResource(image);
    }
}
