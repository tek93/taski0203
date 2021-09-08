package com.example.reqru2.model;

import com.example.reqru2.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class InvoiceInDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String idCustomer;
    private String invoiceId;


}
