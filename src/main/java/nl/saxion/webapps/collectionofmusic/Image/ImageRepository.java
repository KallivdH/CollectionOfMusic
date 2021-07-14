package nl.saxion.webapps.collectionofmusic.Image;

import nl.saxion.webapps.collectionofmusic.lp.LPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
