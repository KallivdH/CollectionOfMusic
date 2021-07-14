package nl.saxion.webapps.collectionofmusic.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.saxion.webapps.collectionofmusic.Image.ImageModel;
import nl.saxion.webapps.collectionofmusic.lp.LPModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ArtistModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    @Column(length=1000)
    private String text;

    @JsonIgnoreProperties("artist")
    @OneToMany(mappedBy = "artist")
    private List<LPModel> discography;

    @JsonIgnoreProperties({"artistModel", "lpModel"})
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageModel image;

    public ArtistModel(String name, String text, ArrayList<LPModel> discography, ImageModel image) {
        this.name = name;
        this.text = text;
        this.discography = discography;
        this.image = image;
    }

    public ArtistModel() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<LPModel> getDiscography() {
        return discography;
    }

    public void setDiscography(List<LPModel> discography) {
        this.discography = discography;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }
}
