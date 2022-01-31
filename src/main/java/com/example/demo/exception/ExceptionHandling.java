package com.example.demo.exception;

import com.example.demo.domain.HttpResponse;
import com.example.demo.exception.domain.NomeJaExistenteException;
import com.example.demo.exception.domain.PaisNaoEncontradoException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    @ExceptionHandler(PaisNaoEncontradoException.class)
    public ResponseEntity<HttpResponse> paisJaEncontradoException(PaisNaoEncontradoException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NomeJaExistenteException.class)
    public ResponseEntity<HttpResponse> nomeJaExistenteException(NomeJaExistenteException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

}
