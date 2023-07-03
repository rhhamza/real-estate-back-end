package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.BadWords;

import java.util.List;

@Repository
public interface BadWordsRepository extends JpaRepository<BadWords, Integer> {

}
