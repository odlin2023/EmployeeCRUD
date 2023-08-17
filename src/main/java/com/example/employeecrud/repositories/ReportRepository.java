package com.example.employeecrud.repositories;


import com.example.employeecrud.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
