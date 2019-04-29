package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.user.User;
import com.jobs.workbook.entites.user.UserSingupResponse;
import com.jobs.workbook.repositories.SettingsRepository;
import com.jobs.workbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@RestController
@CrossOrigin
public class UserContoller {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SettingsRepository settingsRepository;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/detail")
    public User getUserDetail(@RequestParam(required = true, name = "email") String email ) {
        return userRepository.findOneByEmail(email);
    }

    @PostMapping("/user/register")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody User userRequest) throws Exception {

        System.out.print(userRequest);

        User userFromDb = userRepository.findOneByEmail(userRequest.getEmail());

        if (userFromDb == null) {
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
            userRequest.setPassword(encodedPassword);
            if (userRequest.getClientTime() == null) {
                userRequest.setClientTime(System.currentTimeMillis());
            }
            userRequest.setSettings(settingsRepository.save(userRequest.getSettings()));
            this.userRepository.save(userRequest);
            return new ResponseEntity<>(userRequest, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new UserSingupResponse(false, "User with email: " + userRequest.getEmail() + " already exist in database!"), HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) throws  Exception {
        User user = userRepository.findOneById(id);
        if (user != null) {
            userRepository.delete(user);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User with id: " + user.getId() + " is deleted!", HttpStatus.ACCEPTED);

    }

    @PutMapping("/user/update")
    @Transactional
    public ResponseEntity<Object> updateUser(@Valid @RequestBody User user) throws  Exception {
        User userFromDb = userRepository.findOneById(user.getId());
        if (userFromDb != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>( userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
