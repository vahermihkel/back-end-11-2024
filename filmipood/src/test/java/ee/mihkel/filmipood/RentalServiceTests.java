package ee.mihkel.filmipood;

import ee.mihkel.filmipood.entity.Film;
import ee.mihkel.filmipood.entity.FilmType;
import ee.mihkel.filmipood.entity.Rental;
import ee.mihkel.filmipood.model.FilmReturn;
import ee.mihkel.filmipood.repository.FilmRepository;
import ee.mihkel.filmipood.repository.RentalRepository;
import ee.mihkel.filmipood.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RentalServiceTests {

   @Mock
   RentalRepository rentalRepository;

   @Mock
   FilmRepository filmRepository;

   @InjectMocks
   RentalService rentalService; // @Autowired

    List<Film> films = new ArrayList<>();
    Film film1 = new Film();
    Film film2 = new Film();
    Rental rental = new Rental();
    List<FilmReturn> filmReturns = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        film1.setId(1L);
        film1.setAvailable(true);
        film1.setDays(5);
        film2.setId(2L);
        film2.setAvailable(true);
        films.add(film1);
        films.add(film2);
        rental.setId(1L);
        when(rentalRepository.save(any())).thenReturn(rental);
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film1));
        when(filmRepository.findById(2L)).thenReturn(Optional.of(film2));

        FilmReturn filmReturn = new FilmReturn();
        filmReturn.setId(1L);
        filmReturn.setTotalDays(7);
        filmReturns.add(filmReturn);
    }

    // given_when_then()
    @Test
    void givenFilmIsEmpty_whenRentalIsStarted_thenNoSuchElementExceptionIsThrown() throws Exception {
        Film film = new Film();
        film.setId(3L);
        films.add(film);
        assertThrows(NoSuchElementException.class, () -> rentalService.startRental(films));
    }

    @Test
    void givenFilmIsNotAvailable_whenRentalIsStarted_thenExceptionIsThrown() throws RuntimeException {
        film1.setAvailable(false);
        assertThrows(RuntimeException.class, () -> rentalService.startRental(films));
    }

    @Test
    void whenRentalIsStarted_thenLateFeeIs0() throws RuntimeException {
        rental.setLateFee(99);
        rentalService.startRental(films);
        assertEquals( 0, rental.getLateFee());
    }

    @Test
    void givenOldFilmIsRented5Days_whenRentalIsStarted_thenSumIs3() throws RuntimeException {
        film1.setDays(5);
        film1.setType(FilmType.OLD);
        rentalService.startRental(films);
        assertEquals( 3, rental.getSum());
    }

    @Test
    void givenOldFilmIsRented7Days_whenRentalIsStarted_thenSumIs9() throws RuntimeException {
        film1.setDays(7);
        film1.setType(FilmType.OLD);
        rentalService.startRental(films);
        assertEquals( 9, rental.getSum());
    }

    @Test
    void givenNewFilmIsRented7Days_whenRentalIsStarted_thenSumIs28() throws RuntimeException {
        film1.setDays(7);
        film1.setType(FilmType.NEW);
        rentalService.startRental(films);
        assertEquals( 28, rental.getSum());
    }

    @Test
    void givenRegularFilmsIsDelayed2Days_whenRentalIsEnded_thenLateFeeIs6() throws RuntimeException {
        film1.setDays(5);
        film1.setType(FilmType.REGULAR);
        rentalService.startRental(films);
        rentalService.endRental(filmReturns);
        assertEquals( 6, rental.getLateFee());
    }

    @Test
    void givenRegularFilmsIsNotDelayed_whenRentalIsEnded_thenLateFeeIs0() throws RuntimeException {
        film1.setDays(7);
        film1.setType(FilmType.REGULAR);
        rentalService.startRental(films);
        rentalService.endRental(filmReturns);
        assertEquals( 0, rental.getLateFee());
    }

    @Test
    void whenRegularFilmIsRented_thenBonusPointsAre1() {
        film1.setDays(2);
        film1.setType(FilmType.REGULAR);
        rentalService.startRental(films);
        rentalService.endRental(filmReturns);
        assertEquals(1, rentalService.getBonusPoints());
    }

    @Test
    void whenNewFilmIsRented_thenBonusPointsAre2() {
        film1.setDays(2);
        film1.setType(FilmType.NEW);
        rentalService.startRental(films);
        rentalService.endRental(filmReturns);
        assertEquals(2, rentalService.getBonusPoints());
    }


}
