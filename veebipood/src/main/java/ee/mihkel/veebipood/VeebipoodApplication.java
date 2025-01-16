package ee.mihkel.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeebipoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeebipoodApplication.class, args);

//		public BookDto updateBook(UUID bookId, BookDto bookDto) {
//			Book existingBook = bookRepository.findById(bookId)
//					.orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
//
//			// Preserve data that shouldn't be updated
//			LocalDate originalAddedDate = existingBook.getAdded();
//			Integer currentCheckOutCount = existingBook.getCheckOutCount();
//			BookStatus currentStatus = existingBook.getStatus();
//			LocalDate currentDueDate = existingBook.getDueDate();
//			List<CheckOut> currentCheckOuts = existingBook.getCheckOuts();
//
/*
			bookDto.setAdded(existingBook.getAdded());
			bookDto.setCurrenctCheckOutCount(existingBook.getCheckOutCount())
			jne

 */
//			// Map the updated data
//			modelMapper.map(bookDto, existingBook);
//
//			// Restore preserved data
//			existingBook.setId(bookId); // Ensure ID doesn't change
//
//			Book savedBook = bookRepository.save(existingBook);
//			return modelMapper.map(savedBook, BookDto.class);
//		}
	}

}

// Eraldi inimene logifailidele
// Eraldi inimene CSS-le

//6.  26.11
// DTO -> Data Transfer Object+++
// @Log4j2+++ --> logifailid
// @Autowired+++
// ResponseEntity+++
// Swagger+++
// SortingPagination+++
// Front-endis turvalisus

//7.  28.11 --> filmipood
// Unit Testid
// Integratsiooni testid

//8.  03.12
// Unit Testid
// Integratsiooni testid

//9.  05.12 - 17.45-21.00
// VEAHALDUS - Exceptionid

// FRONTEND:
// filtreerimine kategooriate alusel
// leheküljed - Pagination


//10.  10.12   18.30-21.00
// Päringud rakendusest välja -> RestTemplate (pakiautomaadid)

//11.N 12.12   17.45-21.00
//Elektrihinnad + Makse (tagasi kaupmehe juurde)

//12.T 17.12   17.45-21.00
// Autentimine
// Spring Security

//13.N 19.12
// Autentimine Front-Endis
// Veahaldus front-endis
// Kategooriad drop-downina

//14.E 23.12 - 17.45-21.00
// Rollid++
// nuppude peitmine Navbaris kui pole sisse logitud++
// nupu näitamine sisselogitud staatust arvestades++


//15.E 03.01 - 17.45-21.00
// sobivad veateated kui autentimisega vead

// subject --> Ostukorvi kogusumma Navbaris.+++
// angularis tõlge+++
// ühe toote vaatamist+++
// toote muutmine+++
// characteristikud check-boxidena+++
// Avalehel ei tööta Kategooria peale vajutused+++

// otsingumootor++
// Kui on kategooria mõnes tootes ei saa seda kustutada, Tootega sama++
// Kui muudame Adminis kedagi, et ta ei läheks lõppu++
// Registreerudes ---> Suunab Login lehele, et logiks sisse.++

// Shell-script
// Profiilid - LIVE / TEST keskkonna vahetus
// cron

// Kui kategooria all pole ühtegi toodet, mis teeme???

// Allkirjaleht
//16.N 09.01 ---> kui teen refreshi, siis peab enne kasutaja leidma kui guardi sisse läheb
//17.T 14.01
//18.N 16.01

//Tegelikult:
//16.N 09.01   17.45-21.00 ---> Githubist vaatame: CGI, kümnevõistlus, bowling
//17.N 16.01   17.45-21.00
//18.N 23.01  lõpuprojekti esitlemine  17.45-20.00  -- CSS
