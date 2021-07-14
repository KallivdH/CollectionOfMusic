package nl.saxion.webapps.collectionofmusic.lp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.saxion.webapps.collectionofmusic.Image.ImageModel;
import nl.saxion.webapps.collectionofmusic.artist.ArtistModel;

import javax.persistence.*;

@Entity
public class LPModel {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Lob
    @Column(length=1000)
    private String text;

    @JsonIgnoreProperties("discography")
    @ManyToOne
    private ArtistModel artist;

    @JsonIgnoreProperties({"lpModel", "artistModel"})
    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageModel image;

    public LPModel(){}

    public LPModel(String name, String text,ArtistModel artist, ImageModel image) {
        this.name = name;
        this.artist = artist;
        this.text = text;
        this.image = image;
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

    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel artist) {
        this.artist = artist;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "LPModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", text='" + text + '\'' +
                ", image=" + image +
                '}';
    }
}
