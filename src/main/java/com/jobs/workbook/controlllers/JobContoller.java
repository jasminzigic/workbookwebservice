package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.job.Job;
import com.jobs.workbook.entites.user.User;
import com.jobs.workbook.repositories.*;
import com.jobs.workbook.utils.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class JobContoller {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @GetMapping("/jobs/{userId}")
    public List<Job> getAllJobs(@PathVariable long userId, @RequestParam(required = false, name = "customerId") Long customerId){
        if (customerId != null) {
            return jobRepository.findAllByUserIdAndCustomerId(userId, customerId);
        } else {
            return jobRepository.findAllByUserId(userId);
        }
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteJobRecord(@PathVariable long id) {
        Optional<Job> jobToDelete = jobRepository.findById(id);
        DeleteResponse deleteResponse;
        if (jobToDelete.isPresent()) {
            deleteResponse = new DeleteResponse("info", "Job is successfully deleted!", jobToDelete.get().getId());
            jobRepository.delete(jobToDelete.get());
            return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
        }
        deleteResponse = new DeleteResponse("warning", "Job is not found!", null);
        return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.ALREADY_REPORTED);
    }

    @PutMapping("/jobs/update")
    public ResponseEntity<Object> updateJob(@RequestBody Job jobToUpdate) {
        Job job =jobRepository.findById(jobToUpdate.getId()).get();
        if (job != null) {
            job.setCustomer(jobToUpdate.getCustomer());
            job.setDescription(jobToUpdate.getDescription());
            job.setValue(jobToUpdate.getValue());
            job.setClientTime(jobToUpdate.getClientTime());
            job.setLocation(jobToUpdate.getLocation());
            jobRepository.save(job);
            return new ResponseEntity<>(job, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(jobToUpdate, HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping("/job/register")
    public ResponseEntity<Object> addJob(@Valid @RequestBody Job newJobRecord) throws Exception {

        User loggedUser = userRepository.findOneByEmail("zigic.jasmin@gmail.com");

        newJobRecord.setUser(loggedUser);

        if (newJobRecord.getClientTime() == null) {
            newJobRecord.setClientTime(System.currentTimeMillis());
        }
        return new ResponseEntity<>(jobRepository.save(newJobRecord), HttpStatus.CREATED);
    }

}
