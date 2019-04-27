package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.Customer.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @EntityGraph(attributePaths = "jobList")
    List<Customer> findByName(String name);

    @EntityGraph(attributePaths = "jobList")
    Customer findOneByNameAndOwnerId( String name, long id);

    @EntityGraph(attributePaths = "jobList")
    List<Customer> findAllByOwnerIdOrderByNameAsc(long id);

    @EntityGraph(attributePaths = "jobList")
    List<Customer> findAllByOwnerEmailOrderByNameAsc(String email);

    @EntityGraph(attributePaths = "jobList")
    Customer findOneById(long id);
}
