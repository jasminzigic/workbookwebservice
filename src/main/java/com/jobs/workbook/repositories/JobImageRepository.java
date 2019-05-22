package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.jobImage.JobImage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobImageRepository extends JpaRepository<JobImage, Integer> {

    @EntityGraph(attributePaths = {"job", "job.customer"})
    List<JobImage> findAllByJobUserEmail(String email);

    @EntityGraph(attributePaths = {"job",  "job.customer"})
    List<JobImage> findAllByJobUserEmailAndJobCustomerId(String email, long id);

    @EntityGraph(attributePaths = {"job", "job.customer"})
    JobImage findOneById(Integer id);
}
