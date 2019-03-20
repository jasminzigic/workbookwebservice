package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.Customer.Customer;
import com.jobs.workbook.entites.job.Job;
import com.jobs.workbook.entites.location.GeoLocation;
import com.jobs.workbook.entites.user.User;
import com.jobs.workbook.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JobContoller {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GeoLocationRepository geoLocationRepository;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @PostMapping("/job/register")
    public ResponseEntity<Object> addJob(@Valid @RequestBody Job newJobRecord) throws Exception {

        User loggedUser = userRepository.findOneByEmail("zigic.jasmin@gmail.com");

        newJobRecord.setUser(loggedUser);

        Customer customer = customerRepository.findOneByNameAndOwnerId(newJobRecord.getCustomer().getName(), loggedUser.getId());

        if (customer == null) {
            customer = newJobRecord.getCustomer();
            customer.setOwner(loggedUser);
            customer = customerRepository.save(customer);
        }

        System.out.print("\n\n\n" + customer + "\n\n");
        newJobRecord.setCustomer(customer);

        GeoLocation location = newJobRecord.getLocation();
        if (location != null) {
            geoLocationRepository.save(location);
        }
        if (newJobRecord.getClientTime() == null) {
            newJobRecord.setClientTime(System.currentTimeMillis());
        }
        return new ResponseEntity<>(jobRepository.save(newJobRecord), HttpStatus.CREATED);
    }

}
