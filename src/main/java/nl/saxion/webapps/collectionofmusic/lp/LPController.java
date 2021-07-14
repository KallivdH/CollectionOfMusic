package nl.saxion.webapps.collectionofmusic.lp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/api/v1/lps")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LPController {

    private final LPService lpService;

    @Autowired
    public LPController(LPService lpService) {
        this.lpService = lpService;
    }

    @GetMapping()
    public List<LPModel> getAllLPs(){
        return lpService.getAllLPs();
    }

    @GetMapping("/{id}")
    public LPModel getLPByID(@PathVariable Long id){
        return lpService.getLPByID(id);
    }

    @PostMapping()
    public void addLP(@RequestBody LPModel lp, @RequestParam Long imgId, @RequestParam Long artistId){
        lpService.addLP(lp, imgId, artistId);
    }

    @PutMapping("/{id}")
    public void updateLP(@PathVariable Long id, @RequestBody LPModel lp, @RequestParam Long imgId, @RequestParam Long artistId){
        lpService.updateLP(id, lp, imgId, artistId);
    }

    @PutMapping()
    public void updateLPs(@RequestBody List<LPModel> lps){
        lpService.updateLPs(lps);
    }

    @DeleteMapping("/{id}")
    public void deleteLP(@PathVariable Long id){
        lpService.deleteLP(id);
    }

    @DeleteMapping()
    public void deleteAllLPs(){
        lpService.deleteAllLPs();
    }

}
