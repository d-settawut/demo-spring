package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private static Customer customer;

    @BeforeAll
    static void setUp() {
        customer = Customer.builder().id(1L).customerId("X001").customerName("name").customerPhone("+66000000000").build();
    }

    @Test
    public void getCustomerCaseSuccess() {
        Customer expected = customer;
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(expected));

        Optional<Customer> actual = customerService.getCustomerByCustomerId(customer.getCustomerId());
        assertEquals(expected.getId(), actual.get().getId());
        assertEquals(expected.getCustomerId(), actual.get().getCustomerId());
        assertEquals(expected.getCustomerName(), actual.get().getCustomerName());
        assertEquals(expected.getCustomerPhone(), actual.get().getCustomerPhone());

    }

    @Test
    public void getCustomerCaseNotFound() {
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.empty());

        Optional<Customer> actual = customerService.getCustomerByCustomerId(customer.getCustomerId());
        assertEquals(Optional.empty(), actual);

    }
}