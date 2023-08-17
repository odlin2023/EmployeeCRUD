package com.example.employeecrud.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "new_employee")
public class NewEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String confirmed_password;

    @Column(name = "name", nullable = false)
    private String name;

    private String email;


    private String role;



    public NewEmployee() {
    }

    public NewEmployee(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmed_password() {
        return confirmed_password;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }



    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmed_password(String confirmed_password) {
        this.confirmed_password = confirmed_password;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}