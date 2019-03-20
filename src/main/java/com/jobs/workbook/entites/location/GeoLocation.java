package com.jobs.workbook.entites.location;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobs.workbook.entites.job.Job;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GeoLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    private long longitude;

    @NotNull
    private long latitude;

    private String address;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Job> jobList = new ArrayList<>();

    public GeoLocation() {
    }

    public GeoLocation(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
