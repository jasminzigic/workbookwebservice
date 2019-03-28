package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.location.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLocationRepository extends JpaRepository<GeoLocation, Integer> {
    GeoLocation findOneById(long id);
}
