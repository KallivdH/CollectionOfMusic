package nl.saxion.webapps.collectionofmusic.artist;

import nl.saxion.webapps.collectionofmusic.lp.LPModel;
import nl.saxion.webapps.collectionofmusic.lp.LPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping()
    public List<ArtistModel> getAllArtists(){
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ArtistModel getArtistByID(@PathVariable Long id){
        return artistService.getArtistByID(id);
    }

    @PostMapping()
    public void addArtist(@RequestBody ArtistModel artist, @RequestParam Long imgId){
        artistService.addArtist(artist, imgId);
    }

    @PutMapping("/{id}")
    public void updateArtist(@PathVariable Long id, @RequestBody ArtistModel artist, @RequestParam Long imgId){
        artistService.updateArtist(id, artist, imgId);
    }

    @PutMapping()
    public void updateArtists(@RequestBody List<ArtistModel> artists){
        artistService.updateAllArtist(artists);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
    }

    @DeleteMapping()
    public void deleteAllArtists(){
        artistService.deleteAllArtists();
    }

}
