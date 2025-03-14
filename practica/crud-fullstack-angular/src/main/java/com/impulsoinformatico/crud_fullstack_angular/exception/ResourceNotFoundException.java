// Autor: Ian González Quiñonez
// Clase que define una excepción personalizada para recursos no encontrados.
// - Extiende RuntimeException para manejar errores en tiempo de ejecución.
// - Contiene un mensaje descriptivo del error.
// - Se utiliza en el GlobalExceptionHandler para devolver un código HTTP adecuado.

package com.impulsoinformatico.crud_fullstack_angular.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException {
    private String message;
    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
