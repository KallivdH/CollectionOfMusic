package nl.saxion.webapps.collectionofmusic.Image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.saxion.webapps.collectionofmusic.artist.ArtistModel;
import nl.saxion.webapps.collectionofmusic.lp.LPModel;

import javax.persistence.*;

@Entity
public class ImageModel {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    byte[] content;

    @JsonIgnoreProperties("image")
    @OneToOne(mappedBy = "image")
    private LPModel lpModel;

    @JsonIgnoreProperties("image")
    @OneToOne(mappedBy = "image")
    private ArtistModel artistModel;

    public ImageModel() {

    }

    public ImageModel(String name, byte[] content, LPModel lpModel) {
        this.name = name;
        this.content = content;
        this.lpModel = lpModel;
    }

    public ImageModel(String name, byte[] content, ArtistModel artistModel) {
        this.name = name;
        this.content = content;
        this.artistModel = artistModel;
    }

    public ArtistModel getArtistModel() {
        return artistModel;
    }

    public void setArtistModel(ArtistModel artistModel) {
        this.artistModel = artistModel;
    }

    public LPModel getLpModel() {
        return lpModel;
    }

    public void setLpModel(LPModel lpModel) {
        this.lpModel = lpModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
