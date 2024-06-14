package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.request.GetCustomerRequest;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private static Customer customer;

    private static GetCustomerRequest getCustomerRequest;

    @BeforeAll
    static void setUp() {
        customer = Customer.builder().id(1L).customerId("X001").customerName("name").customerPhone("+66000000000").build();

        getCustomerRequest = new GetCustomerRequest();
        getCustomerRequest.setCustomerId("x001");
    }

    @Test
    public void testGetCustomerCaseSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(getCustomerRequest);

        when(customerService.getCustomerByCustomerId(anyString())).thenReturn(Optional.of(customer));
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/getCustomer").content(requestJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetCustomerCaseFailedInvalidRequest() throws Exception {
        GetCustomerRequest request = new GetCustomerRequest();
        request.setCustomerId(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/getCustomer").content(requestJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void testGetCustomerCaseNotFound() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(getCustomerRequest);

        when(customerService.getCustomerByCustomerId(anyString())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/getCustomer").content(requestJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
    }
}