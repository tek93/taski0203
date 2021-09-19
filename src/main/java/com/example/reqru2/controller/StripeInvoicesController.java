package com.example.reqru2.controller;

import com.example.reqru2.dto.InvoiceDto;
import com.example.reqru2.service.StripleService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class StripeInvoicesController {
    @Autowired
    private StripleService stripleService;

    @GetMapping("/api/invo/createInvoice")
    public ResponseEntity createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) throws StripeException, IOException {

        return new ResponseEntity<>(stripleService.putInvo(invoiceDto), HttpStatus.CREATED);
    }

    @GetMapping("/api/invo/getInvoice")
    public String getInvoices() throws StripeException, IOException {
        return stripleService.getAllInvoiceFromUser();
    }

    @GetMapping("/api/invo/createUser")
    public void createUserStriple() throws StripeException {
        stripleService.createCustomerForStripple();
    }


}
