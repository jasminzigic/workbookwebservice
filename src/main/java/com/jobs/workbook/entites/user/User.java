package com.jobs.workbook.entites.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.jobs.workbook.entites.Customer.Customer;
import com.jobs.workbook.entites.job.Job;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name ="id")
    private long id;

    private String name;

    @Email
    private String email;

    @Pattern(regexp="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    private String phone;

    @JsonIgnore
    @NotNull
    @Column(name = "password")
    @JsonSetter("password")
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE
    )
    @JsonIgnore
    private List<Job> jobList = new ArrayList<>();

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.MERGE
    )
    @JsonIgnore
    private List<Customer> customerList = new ArrayList<>();

    @JoinColumn(name = "client_time")
    private long clientTime;

      public User() {
          this.clientTime = System.currentTimeMillis();
      }

    public User(String name, @Email String email, @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$") String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clientTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
