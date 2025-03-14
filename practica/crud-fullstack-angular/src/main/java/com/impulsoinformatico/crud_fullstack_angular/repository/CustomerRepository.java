// Autor: Ian González Quiñonez
// Este archivo define una interfaz 'CustomerRepository' que extiende JpaRepository.
// El propósito de esta interfaz es proporcionar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
// para la entidad 'Customer', utilizando la clave primaria de tipo Integer.
package com.impulsoinformatico.crud_fullstack_angular.repository;

import com.impulsoinformatico.crud_fullstack_angular.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Integer>{
}
