package com.jobs.workbook.entites.jobImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.jobs.workbook.entites.job.Job;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "job_images")
public class JobImage {
    @Id
    @GeneratedValue
    Integer id;

    String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonSetter("job")
    @NotNull
    @JsonIgnore
    @JoinColumn(name = "job_id", updatable = false)
    Job job;

    public JobImage() {
    }

    public JobImage(Integer id, String url, Job job) {
        this.id = id;
        this.url = url;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getJobId() {
        return this.job.getId();
    }

    public String getTitle() {
        return this.job.getCustomer().getName();
    }

    public long getCustomerId() {
        return this.job.getCustomer().getId();
    }
}
