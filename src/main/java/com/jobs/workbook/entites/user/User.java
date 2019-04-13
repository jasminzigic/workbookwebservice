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
            fetch = FetchType.EAGER
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
    private Long clientTime;

      public User() {
      }

    public User(String name, @Email String email, @NotNull String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public Long getClientTime() {
        return clientTime;
    }

    public void setClientTime(Long clientTime) {
        this.clientTime = clientTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (jobList != null ? !jobList.equals(user.jobList) : user.jobList != null) return false;
        if (customerList != null ? !customerList.equals(user.customerList) : user.customerList != null) return false;
        return clientTime != null ? clientTime.equals(user.clientTime) : user.clientTime == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (jobList != null ? jobList.hashCode() : 0);
        result = 31 * result + (customerList != null ? customerList.hashCode() : 0);
        result = 31 * result + (clientTime != null ? clientTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", jobList=" + jobList +
                ", customerList=" + customerList +
                ", clientTime=" + clientTime +
                '}';
    }

    public double getTotalValue() {
        return  this.jobList.stream().mapToDouble(Job::getValue).sum();
    }
}
