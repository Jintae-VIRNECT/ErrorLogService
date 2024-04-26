package error.api.config;

import error.api.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse> validException(
            MethodArgumentNotValidException ex) {

        RestResponse restResponse = new RestResponse(false, // 1
                "유효성 검사 실패 : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST); // 2
    }
}
