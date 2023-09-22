package pl.wisniea.empik.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.wisniea.empik.communication.dto.client.response.ErrorResponse;

@RestControllerAdvice
public class GithubExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GithubServiceNotAvailableException.class})
    public ResponseEntity<ErrorResponse> handleGithubServiceNotAvailableException(GithubServiceNotAvailableException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR,
          exception.getMessage(),
          request.getDescription(false)),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
          HttpStatus.NOT_FOUND,
          exception.getMessage(),
          request.getDescription(false)),
          HttpStatus.NOT_FOUND);
    }
}
