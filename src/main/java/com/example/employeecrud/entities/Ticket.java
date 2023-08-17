package com.example.employeecrud.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @Column(name = "name")
    private String name;
    private String department;
    private String phoneNumber;
    private String email;
    private String assignedTickets;
    private String content;
    private String sender;
    private String receiver;
    private LocalDateTime time;
    private String priority;
    @Column(nullable = false, unique = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "new_employee_id") // Assuming the foreign key column name
    private NewEmployee newEmployee;

    // Default constructor
    public Ticket() {
    }

    // Constructor with all the fields
    public Ticket(String status, String name, String department, String phoneNumber, String email,
                  String assignedTickets, String content, String sender, String receiver, LocalDateTime time, String priority) {
        this.status = status;
        this.name = name;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.assignedTickets = assignedTickets;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.priority = priority;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(String assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
