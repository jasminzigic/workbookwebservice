package com.jobs.workbook.entites.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.jobs.workbook.entites.Customer.Customer;
import com.jobs.workbook.entites.location.GeoLocation;
import com.jobs.workbook.entites.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Job {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSetter("customer")
    @NotNull
    @JsonIgnore
    private Customer customer;

    @Column(name = "description",columnDefinition="LONGTEXT" )
    private String description;

    @OneToOne(fetch = FetchType.LAZY,  orphanRemoval = true)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSetter("location")
    private GeoLocation location;

    @Column(name = "value")
    @NotNull
    private long value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @JoinColumn(name = "client_time")
    private Long clientTime;

    public Job() {

    }

    public Job(long id, @NotNull Customer customer, String description, GeoLocation location, @NotNull long value, User user, Long clientTime) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.location = location;
        this.value = value;
        this.user = user;
        this.clientTime = clientTime;
    }

    public Job(Customer customer, String description, GeoLocation location, long value, User user) {
        this.customer = customer;
        this.description = description;
        this.location = location;
        this.value = value;
        this.user = user;
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

    public Long getClientTime() {
        return clientTime;
    }

    public void setClientTime(Long clientTime) {
        this.clientTime = clientTime;
    }

    public String getCustomerName() {
        return this.customer.getName();
    }

    public Date getDate() {
        return new Date(this.clientTime);
    }

}
