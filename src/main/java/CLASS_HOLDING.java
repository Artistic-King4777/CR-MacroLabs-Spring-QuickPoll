import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

public class CLASS_HOLDING {
}

////////////////////////////////////////////////////////////////////////////





package com.example.javacohort3.ZipCodeBank.controllers;

        import com.example.javacohort3.ZipCodeBank.domains.Customer;
        import com.example.javacohort3.ZipCodeBank.exceptions.ResourceNotFoundException;
        import com.example.javacohort3.ZipCodeBank.services.CustomerService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

        import java.net.URI;
        import java.util.ArrayList;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get a Customer by their Account ID
    @RequestMapping(value ="/accounts/{accountId}/customer", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
        HttpStatus status = HttpStatus.OK;
        Customer customer = customerService.getCustomerByAccountId(accountId);

        log.info("[GET BY ACCOUNT ID]: " + customer);
        return new ResponseEntity<>(customer, status);
    }

    // Get all Customers
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomers() {
        HttpStatus status = HttpStatus.OK;

        ArrayList<Customer> customers = customerService.getAllCustomers();

        log.info("[GET ALL PEOPLE]: " + customers);
        return new ResponseEntity<>(customers, status);
    }

    // Get Customer By their ID
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        Customer customer = customerService.getCustomerById(id);

        // throw error if (customer == null)
        customerService.verifyCustomer(id);

        //if they do exist
        log.info("[GET BY ID]: " + customer);

        return new ResponseEntity<>(customer, status);
    }

    // Create a new Customer
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        HttpStatus status = HttpStatus.CREATED;
        Customer c = customerService.createCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/id")
                .buildAndExpand(c.getId())
                .toUri();
        httpHeaders.setLocation(newUri);

        log.info("[POST " + c);
        return new ResponseEntity<>(c, status);
    }

    // Update a Customer by their ID
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        customerService.updateCustomer(customer, id);
        customerService.verifyCustomer(id);

        log.info("[PUT-UPDATE]: " + customer);
        return new ResponseEntity<>(customer, status);
    }

}


















