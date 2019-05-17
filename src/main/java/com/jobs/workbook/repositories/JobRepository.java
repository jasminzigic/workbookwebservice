package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.job.Job;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @EntityGraph(attributePaths = {"location", "customer", "images"})
    List<Job> findAllByUserId(long id);

    @EntityGraph(attributePaths = {"location", "customer", "images"})
    List<Job> findAllByUserEmail(String email);

    @EntityGraph(attributePaths = {"location", "customer", "images"})
    List<Job> findAllByUserEmailAndCustomerId(String email, long customerId);

    @EntityGraph(attributePaths =  {"location", "customer", "images"})
    List<Job> findAllByUserIdAndCustomerId(long userId, long customerId);

    @EntityGraph(attributePaths = {"location", "customer", "images"})
    Optional<Job> findById(long id);
}
