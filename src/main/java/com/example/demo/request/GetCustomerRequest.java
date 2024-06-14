package com.example.demo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetCustomerRequest {

    @NotNull(message = "customerId is required")
    private String customerId;
}
