package nl.saxion.webapps.collectionofmusic.lp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LPRepository extends JpaRepository<LPModel, Long> {

}
