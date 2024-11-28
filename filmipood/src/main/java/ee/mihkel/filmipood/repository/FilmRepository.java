package ee.mihkel.filmipood.repository;

import ee.mihkel.filmipood.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {


    List<Film> findByAvailableTrue();
}
