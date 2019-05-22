package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.jobImage.JobImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobImageRepository extends JpaRepository<JobImage, Integer> {

}
