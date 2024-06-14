package com.example.demo.mapper;

import com.example.demo.entity.Customer;
import com.example.demo.model.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerMapperTest {
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTOMap() {
        Customer customer = Customer.builder().id(1L).customerId("X001").customerName("customer name").customerPhone("+66000000000").build();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(customerDTO.getCustomerId(), customer.getCustomerId());
        assertEquals(customerDTO.getCustomerName(), customer.getCustomerName());
    }

}