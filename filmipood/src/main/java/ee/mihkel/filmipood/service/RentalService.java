package ee.mihkel.filmipood.service;

import ee.mihkel.filmipood.entity.Film;
import ee.mihkel.filmipood.entity.Rental;
import ee.mihkel.filmipood.model.FilmReturn;
import ee.mihkel.filmipood.repository.FilmRepository;
import ee.mihkel.filmipood.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// KODUS: Nortali töö
// KODUS: Siia Rentalisse front-end

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    FilmRepository filmRepository;

    // kui on siin final, siis Java mõistes on tegemist const ehk väärtust ei saa enam rohkem anda
    private final int PREMIUM_PRICE = 4;
    private final int BASIC_PRICE = 3;


    //@Transactional // keerab kõik andmebaasitegemised tagasi siit funktsioonist kui tuli kuskil kohas exception
    public Rental startRental(List<Film> films) throws Exception {
        for (Film film: films) {
            Film dbFilm = filmRepository.findById(film.getId()).orElseThrow();
            if (!dbFilm.isAvailable()) {
                throw new Exception("Film with id " + dbFilm.getId() + " not available!");
            }
        }

        Rental rental = new Rental(); // Rentali sees on kõik väljad tühjad
        rental = rentalRepository.save(rental); // saving to get ID
        int sum = 0;
        for (Film frontendFilm :films) {
            Film dbFilm = filmRepository.findById(frontendFilm.getId()).orElseThrow();
            dbFilm.setDays(frontendFilm.getDays());
            dbFilm.setAvailable(false);
            dbFilm.setRental(rental);
            filmRepository.save(dbFilm);

            sum += calculateFilmPrice(dbFilm);
        }
        rental.setSum(sum);
        rental.setLateFee(0);
        rentalRepository.save(rental);
        return rental;
    }

    private int calculateFilmPrice(Film dbFilm) {
        switch (dbFilm.getType()) {
            case NEW -> {
                //10päeva: 10*4 = 40
                return dbFilm.getDays() * PREMIUM_PRICE;
            }
            case REGULAR -> {
                //10päeva:  3 + 3*7 = 3+21=24
                if (dbFilm.getDays() > 3) {
                    return BASIC_PRICE + BASIC_PRICE * (dbFilm.getDays()-3);
                }
                return BASIC_PRICE;
            }
            case OLD -> {
                //10päeva:  3 + 3*5 = 3+15=18
                if (dbFilm.getDays() > 5) {
                    return BASIC_PRICE + BASIC_PRICE * (dbFilm.getDays()-5);
                }
                return BASIC_PRICE;
            }
            case null, default -> {
                return 0;
            }
        }
    }

    public List<Rental> endRental(List<FilmReturn> films) {
        for (FilmReturn f: films) {
            Film film = filmRepository.findById(f.getId()).orElseThrow();
            film.setDays(0); // enne kindlasti lateFee arvutamine
            film.setAvailable(true);
            Rental rental = film.getRental();
            if (f.getTotalDays() > film.getDays()) {
                rental.setLateFee(rental.getLateFee() + calculateLateFee(film, f.getTotalDays()));
            }
            rentalRepository.save(rental);
            film.setRental(null);
            filmRepository.save(film);
        }
        return rentalRepository.findAll();
    }

    private int calculateLateFee(Film film, int totalDays) {
        switch (film.getType()) {
            case NEW -> {
                return (totalDays - film.getDays()) * PREMIUM_PRICE;
            }
            case REGULAR, OLD -> {
                return (totalDays - film.getDays()) * BASIC_PRICE;
            }
            case null, default -> {
                return 0;
            }
        }
    }
}
