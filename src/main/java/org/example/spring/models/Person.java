package org.example.spring.models;

import jakarta.validation.constraints.*;
import org.example.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class Person {
    private int id;
    @NotBlank(message = "Empty surname")
    @Size(min = 0, max = 100, message = "Surname is too big")
    private String surname;
    @NotBlank(message = "Empty name")
    @Size(min = 0, max = 100, message = "Name is too big")
    private String name;
    @Min(value = 0, message = "You can not use the negative number")
    @Max(value = 80, message = "You can not use the number over 80")
    private int age;
    @NotBlank(message = "Empty email")
    @Email(message = "It is not the email")
    private String email;

    public Person(){}

    public Person(int id, String surname, String name, int age, String email){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Person) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person[" +
                "id=" + id + ", " +
                "name=" + name + ']';
    }

}
