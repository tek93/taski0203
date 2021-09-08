package com.example.reqru2.model;

import com.example.reqru2.dto.InvoiceDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")

    @Column(name="name")

    private String name;
    @NotBlank(message = "Username is required")
    @Column(name="username")
    private String username;

    @NotBlank(message = "Password is required")
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Transient
    private String token;
    @Column(name="stripe_id")
    private String stripeId;
    @Column(name="invoice_dto_list")
    @OneToMany(mappedBy="id")

    private List<InvoiceInDb> invoiceInDbs;

}
