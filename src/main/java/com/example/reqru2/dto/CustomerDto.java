package com.example.reqru2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String id;
        private String object;
        private String account_country;
        private String account_name;
        private String amount_due;
}

