package ee.mihkel.veebipood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

// Tuleb peal globaalselt kõikidele Controlleritele ja hakkab nende veateateid (Exceptione) kinni püüdma
@ControllerAdvice
public class ExceptionCatcher {

    @ExceptionHandler       // püüan kinni selle exceptioni mille kirjutan siia sulgude sisse
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(new Date());
        errorMessage.setName(e.getMessage());
        errorMessage.setStatusCode(400);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler       // püüan kinni selle exceptioni mille kirjutan siia sulgude sisse
    public ResponseEntity<ErrorMessage> handleException(NoSuchElementException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(new Date());
        errorMessage.setName(e.getMessage());
        errorMessage.setStatusCode(404);
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
