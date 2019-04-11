package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByName(String name);
    Customer findOneByNameAndOwnerId( String name, long id);
    List<Customer> findAllByOwnerIdOrderByNameAsc(long id);
    List<Customer> findAllByOwnerEmailOrderByNameAsc(String email);
    Customer findOneById(long id);
}
