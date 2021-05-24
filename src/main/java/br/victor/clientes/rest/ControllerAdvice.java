package br.victor.clientes.rest;

import br.victor.clientes.rest.exception.ApiErrors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();
        List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ApiErrors(errors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrors> handleResponseStatusException(ResponseStatusException ex) {
        String mensagemErro = ex.getReason();
        HttpStatus status = ex.getStatus();
        var apiErrors = new ApiErrors(mensagemErro);

        return new ResponseEntity<>(apiErrors, status);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiErrors> handleResponseStatusException(DateTimeParseException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        var apiErrors = new ApiErrors("Parametro data invalido!");

        return new ResponseEntity<>(apiErrors, status);
    }

}
