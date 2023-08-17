package com.example.employeecrud.repositories;



import com.example.employeecrud.entities.NewEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<NewEmployee, Long> {
    NewEmployee findByUsername(String username);
}