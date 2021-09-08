package com.example.reqru2.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Data
public class UserDto {
    private String name;

    private String username;
}
