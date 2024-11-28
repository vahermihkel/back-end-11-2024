package ee.mihkel.filmipood.repository;

import ee.mihkel.filmipood.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
