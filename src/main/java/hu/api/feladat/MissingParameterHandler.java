package hu.api.feladat;

import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import hu.api.feladat.Result.Error;

@ControllerAdvice
public final class MissingParameterHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error<String>> handleMissingParams(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new Error<>("Hiányzó '" + ex.getParameterName() + "' paraméter!"), HttpStatus.BAD_REQUEST);
    }
}
