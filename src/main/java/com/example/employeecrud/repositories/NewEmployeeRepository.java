package com.example.employeecrud.repositories;


import com.example.employeecrud.entities.NewEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewEmployeeRepository extends JpaRepository<NewEmployee, Long> {
    NewEmployee findByUsername(String username);

    NewEmployee findByEmail(String email);

    List<NewEmployee> findByRoleContains(String role);




}
