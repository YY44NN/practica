// Autor: Ian González Quiñonez
// Clase que maneja globalmente las excepciones en la aplicación.
// - Usa @ControllerAdvice para capturar excepciones en toda la aplicación.
// - Define un manejador específico para ResourceNotFoundException.
// - Crea una respuesta estructurada con detalles del error, incluyendo la marca de tiempo,
//   el mensaje de error, la ruta de la solicitud y un código de error personalizado.
// - Devuelve un ResponseEntity con un código de estado HTTP 404 (NOT FOUND).
package com.impulsoinformatico.crud_fullstack_angular.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                        WebRequest webRequest) {

    ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false), "Resource not found"


    );
    return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }

}