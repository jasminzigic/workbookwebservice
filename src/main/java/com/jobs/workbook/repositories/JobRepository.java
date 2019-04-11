package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findAllByUserId(long id);
    List<Job> findAllByUserEmail(String email);
    List<Job> findAllByUserEmailAndCustomerId(String email, long customerId);
    List<Job> findAllByUserIdAndCustomerId(long userId, long customerId);
    Optional<Job> findById(long id);
}
