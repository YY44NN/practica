// Autor: Ian González Quiñonez
// Entidad JPA que representa la tabla "customers" en la base de datos.
// - @Entity: Indica que esta clase es una entidad gestionada por JPA.
// - @Table: Define el nombre de la tabla en la base de datos.
// - @Id y @GeneratedValue: Especifican que "id" es la clave primaria y se genera automáticamente.
// - @Column: Restringe que "email" debe ser único y no puede ser nulo.
// Esta clase incluye los atributos, constructor y métodos getter y setter.
package com.impulsoinformatico.crud_fullstack_angular.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String email;

    public Customer() {
    }


    public Customer(Integer id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
