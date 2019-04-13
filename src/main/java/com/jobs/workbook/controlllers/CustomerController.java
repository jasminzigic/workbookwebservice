package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.Customer.Customer;
import com.jobs.workbook.entites.user.User;
import com.jobs.workbook.repositories.CustomerRepository;
import com.jobs.workbook.repositories.UserRepository;
import com.jobs.workbook.utils.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/jpa/customers")
    public List<Customer> getAllCustomers(Principal principal) {
    	 User loggedUser = userRepository.findOneByEmail(principal.getName());
        return customerRepository.findAllByOwnerEmailOrderByNameAsc(loggedUser.getEmail());
    }

    @PostMapping("/jpa/customer/register")
    public ResponseEntity registerCustomer(@RequestBody Customer customer,  Principal principal) {
    	User loggedUser = userRepository.findOneByEmail(principal.getName());
        Customer customerFromDb = customerRepository.findOneByNameAndOwnerId(customer.getName(), loggedUser.getId());
        if (customerFromDb != null) {
            return new ResponseEntity(customerFromDb, HttpStatus.ALREADY_REPORTED);
        } else {
            customer.setOwner(loggedUser);
            return new ResponseEntity(customerRepository.save(customer), HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/jpa/customer/delete/{customerId}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable long customerId) throws Exception {
        Customer customerFromDb = customerRepository.findOneById(customerId);
        DeleteResponse deleteResponse;
        if(customerFromDb != null) {
            deleteResponse = new DeleteResponse("info", "Customer is successfully deleted!", customerFromDb.getId());
            customerRepository.delete(customerFromDb);
            return new ResponseEntity(deleteResponse, HttpStatus.ACCEPTED);
        } else {
            deleteResponse = new DeleteResponse("warning", "Customer not found!", null);
            return new ResponseEntity(deleteResponse, HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping("/jpa/customer/update/")
    public ResponseEntity updateCustomer(@RequestBody Customer customer) {
        Customer customerFromDb = customerRepository.findOneById(customer.getId());
        System.out.print(customer);
        if (customerFromDb != null) {
            customerFromDb.setName(customer.getName());
            customerFromDb.setEmail(customer.getEmail());
            customerFromDb.setPhone(customer.getPhone());
            customerRepository.save(customerFromDb);
            return new ResponseEntity(customerFromDb, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

    }
}
