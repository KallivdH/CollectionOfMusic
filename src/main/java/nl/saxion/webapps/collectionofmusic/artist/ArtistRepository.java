package nl.saxion.webapps.collectionofmusic.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistModel, Long> {

}
