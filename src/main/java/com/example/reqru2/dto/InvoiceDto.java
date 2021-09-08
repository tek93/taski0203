package com.example.reqru2.dto;

import lombok.Data;

@Data
public class InvoiceDto {

    private String id;
    private String customer;
    private Long total;
    private Long subtotal;
    private String object;


}
