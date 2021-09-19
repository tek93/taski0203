package com.example.reqru2.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
   // @UniqueElements(message = "user with this username exist in DB")
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



}
