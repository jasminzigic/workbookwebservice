package com.jobs.workbook.entites.settings;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobs.workbook.entites.user.User;

@Entity
public class Settings {
    @Id
    @GeneratedValue
    long id;

    @OneToOne(mappedBy="settings")
    @JsonIgnore
    User user;


    String country;

    String currencyCode;

    public Settings() {
    }

    public Settings(User user, String country, String currencyCode) {
        this.user = user;
        this.country = country;
        this.currencyCode = currencyCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
