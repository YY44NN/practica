// Autor: Ian González Quiñonez
// Esta clase es la implementación del servicio 'CustomerService'. Implementa los métodos definidos en la interfaz
// 'CustomerService' para realizar operaciones sobre la entidad 'Customer'. Utiliza el repositorio 'CustomerRepository'
// para interactuar con la base de datos y maneja las excepciones relacionadas con los recursos no encontrados.
package com.impulsoinformatico.crud_fullstack_angular.service;

import com.impulsoinformatico.crud_fullstack_angular.Entity.Customer;
import com.impulsoinformatico.crud_fullstack_angular.exception.ResourceNotFoundException;
import com.impulsoinformatico.crud_fullstack_angular.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findALL() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                ()->{
                        throw new ResourceNotFoundException(
                                "Customer con id " + id+ " no encontrado");
                }

        );
        //return customerRepository.findById(id).get();
        return customer;
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
}
