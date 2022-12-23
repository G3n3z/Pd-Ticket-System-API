package PD2022.PDTicketApi.exception;


import PD2022.PDTicketApi.payload.ErrorDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> apiException(ApiException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(errorDetails, exception.getStatus());
    }
}
