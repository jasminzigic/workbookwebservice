package com.jobs.workbook.BasicAuth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BasicAuthController {

    @GetMapping("/basicauth")
    public ResponseEntity<BasicAuthResponse>  basicAuthentication() {
        return new ResponseEntity(new BasicAuthResponse("You are authenticated"), HttpStatus.OK);
    }
}
