package com.example.employeecrud.entities;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "reports") // Specify the desired table name here (e.g., "reports")
@Data // Lombok annotation to generate getters, setters, toString(), etc.
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;


}