package com.example.employeecrud.services;


import com.example.employeecrud.entities.NewEmployee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, NewEmployee> employees = new HashMap<>();



    public NewEmployee findByUsername(String username) {
        return this.employees.get(username);
    }

    public void saveUser(NewEmployee employee) {
        employees.put(employee.getUsername(), employee);
    }
}

