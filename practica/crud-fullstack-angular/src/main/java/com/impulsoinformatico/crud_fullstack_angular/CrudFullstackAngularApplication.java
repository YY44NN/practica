// Autor: Ian González Quiñonez
// Este archivo es la clase principal de la aplicación Spring Boot 'CrudFullstackAngularApplication'.
// Su método 'main' arranca la aplicación usando SpringApplication.run, que se encarga de
// inicializar y ejecutar la aplicación Spring Boot.
package com.impulsoinformatico.crud_fullstack_angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudFullstackAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudFullstackAngularApplication.class, args);
	}

}
