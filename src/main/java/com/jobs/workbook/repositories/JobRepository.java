package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
