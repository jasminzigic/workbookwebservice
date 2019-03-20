package com.jobs.workbook.entites.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.jobs.workbook.entites.Customer.Customer;
import com.jobs.workbook.entites.location.GeoLocation;
import com.jobs.workbook.entites.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSetter("customer")
    @JsonIgnore
    private Customer customer;

    @Column(name = "description",columnDefinition="LONGTEXT" )
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSetter("location")
    private GeoLocation location;

    @Column(name = "value")
    @NotNull
    private long value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @JoinColumn(name = "client_time")
    private long clientTime;

    public Job() {
        this.clientTime = System.currentTimeMillis();
    }

    public Job(Customer customer, String description, GeoLocation location, long value, User user) {
        this.customer = customer;
        this.description = description;
        this.location = location;
        this.value = value;
        this.user = user;
        this.clientTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
