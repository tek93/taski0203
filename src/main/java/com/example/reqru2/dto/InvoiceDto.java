package com.example.reqru2.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InvoiceDto {
        @NotBlank(message = "Product name is required")
    private String productName;
        @NotNull(message = "Quantity cannot be null")
    private Integer qty;
        @NotNull(message = "Unit amount cannot be null")
    private Integer ua;

}
