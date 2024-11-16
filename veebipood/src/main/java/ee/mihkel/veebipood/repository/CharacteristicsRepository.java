package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicsRepository extends JpaRepository<Characteristic, Long> {
}
