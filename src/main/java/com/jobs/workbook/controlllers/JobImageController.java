package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.jobImage.JobImage;
import com.jobs.workbook.repositories.JobImageRepository;
import com.jobs.workbook.utils.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class JobImageController {

    @Autowired
    JobImageRepository jobImageRepository;

    @PostMapping("/jpa/job_images/register")
    JobImage addImage(@RequestBody JobImage newImage) {
        return jobImageRepository.save(newImage);
    }

    @DeleteMapping("/jpa/job_images/delete/{id}")
    ResponseEntity<DeleteResponse> deleteImage(@PathVariable Integer id) {
        Optional<JobImage> imageToDelete = jobImageRepository.findById(id);
        System.out.println(imageToDelete);
        if (imageToDelete.isPresent()) {
            jobImageRepository.delete(imageToDelete.get());
            return new ResponseEntity<DeleteResponse>(new DeleteResponse("success", "Job is successfully deleted!", imageToDelete.get().getId()), HttpStatus.ACCEPTED) ;
        } else {
            return new ResponseEntity<DeleteResponse>(new DeleteResponse("warning", "Job is not found!", null), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
