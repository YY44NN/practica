// Autor: Ian González Quiñonez
// Controlador REST para la gestión de clientes en una aplicación full-stack con Angular y Spring Boot.
// Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) a través de endpoints HTTP.
// - @PostMapping: Guarda un nuevo cliente en la base de datos.
// - @GetMapping: Obtiene todos los clientes o uno en específico mediante su ID.
// - @PutMapping: Actualiza los datos de un cliente existente.
// - @DeleteMapping: Elimina un cliente según su ID.
// Se usa @CrossOrigin para permitir el acceso desde el frontend alojado en http://localhost:4200.
package com.impulsoinformatico.crud_fullstack_angular.controller;

import com.impulsoinformatico.crud_fullstack_angular.Entity.Customer;
import com.impulsoinformatico.crud_fullstack_angular.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
//http://localhost:8080/api/customers
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//http://localhost:8080/api/customers
@PostMapping
    public Customer save( @RequestBody Customer customer){
        return customerService.save(customer);
    }

//http://localhost:8080/api/customers
@GetMapping
    public List<Customer> findAll(){
        return customerService.findALL();
    }

    //http://localhost:8080/api/customers/1
@GetMapping("/{id}")
public Customer findById(@PathVariable Integer id){
        return customerService.findById(id);
}

    //http://localhost:8080/api/customers/1
@DeleteMapping("{id}")
public void deleteById(@PathVariable Integer id){

        customerService.deleteById(id);
}

    //http://localhost:8080/api/customers
@PutMapping
public Customer updateCustomer(@RequestBody Customer customer){
        Customer customerDb = customerService.findById(customer.getId());
        customerDb.setFirstName(customer.getFirstName());
        customerDb.setLastName(customer.getLastName());
        customerDb.setEmail(customer.getEmail());
        return customerService.update(customerDb);

    }
}

