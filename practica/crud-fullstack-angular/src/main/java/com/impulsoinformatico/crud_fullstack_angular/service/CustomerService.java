// Autor: Ian González Quiñonez
// Esta es la interfaz 'CustomerService', que define los métodos que deben implementarse para gestionar
// las operaciones relacionadas con la entidad 'Customer', como guardar, obtener, actualizar y eliminar clientes.

package com.impulsoinformatico.crud_fullstack_angular.service;

import com.impulsoinformatico.crud_fullstack_angular.Entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> findALL();
    Customer findById (Integer id);
    void deleteById(Integer id);
    Customer update(Customer customer);
}
