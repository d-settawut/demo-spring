package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.CustomerDTO;
import com.example.demo.request.GetCustomerRequest;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomer")
    public ResponseEntity<?> getCustomer(@Valid @RequestBody GetCustomerRequest request) {
        logger.info("GetCustomerRequest: {}", request.toString());
        Optional<Customer> customer = customerService.getCustomerByCustomerId(request.getCustomerId());

        if (customer.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found");

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer.get());
        logger.info("GetCustomerResponse: {}", customerDTO.toString());
        return ResponseEntity.ok(customerDTO);
    }
}
