package com.example.employeecrud.repositories;


import com.example.employeecrud.entities.Ticket;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@NamedQuery(
        name = "TicketRepository.search",
        query = "SELECT t FROM Ticket t WHERE t.name LIKE :name"
)
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Long countByStatus(String status);

    List<Ticket> findByNameContaining(String name);

    Ticket findByName(String name);

    @Query("SELECT t FROM Ticket t WHERE "
            + "LOWER(t.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
            + "LOWER(t.status) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
            + "LOWER(t.department) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
            + "LOWER(t.email) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Ticket> search(@Param("searchTerm") String searchTerm);
    @Transactional
    @Modifying
    void deleteByIdIn(List<Long> ids);
    @Query("SELECT t FROM Ticket t WHERE t.newEmployee.name LIKE %:name%")
    List<Ticket> findByNewEmployee_NameContaining(@Param("name") String name);


    @Query("SELECT t FROM Ticket t WHERE t.name = :employeeName")
    List<Ticket> findByEmployeeName(@Param("employeeName") String employeeName);




}
