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
    private Float longitude;

    @NotNull
    private Float latitude;

    private String address;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Job job;

    public GeoLocation() {
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public GeoLocation(Float longitude, Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
