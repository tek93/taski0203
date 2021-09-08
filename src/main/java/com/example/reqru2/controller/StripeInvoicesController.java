package com.example.reqru2.controller;

import com.example.reqru2.dto.CustomerDto;
import com.example.reqru2.model.InvoiceInDb;
import com.example.reqru2.model.User;
import com.example.reqru2.repository.InvoiceRepository;
import com.example.reqru2.service.StripleService;
import com.example.reqru2.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequiredArgsConstructor
@RestController
public class StripeInvoicesController {
    @Autowired
    private    StripleService stripleService;

    @GetMapping("/api/invo/createInvoice")
    public Invoice createInvoice(String productName, Integer qty, Integer ua) throws StripeException {
      return   stripleService.putInvo(productName,qty,ua);
    }
    @GetMapping("/api/invo/createUser")
    public void createUserStriple() throws StripeException {
        stripleService.createCustomerForStripple();
    }















//    @GetMapping("/api/invo/all")
//    private InvoiceCollection getAllInvoiceForUser() throws StripeException {
//    // this method is in work
//
//        Stripe.apiKey = stripKey;
//
//        Map<String, Object> params = new HashMap<>();
//
//        System.out.println(getUser().getStripeId());
//
//
//        Map<String, Object> params4 = new HashMap<>();
//        params.put("limit", 100);
//
//        InvoiceCollection invoices = Invoice.list(params4);
//
//                return         invoices;
//        }

//          User user =   getUser();
//
//            List<InvoiceInDb>  invoiceDtoList= invoiceRepository.findAllByUserId(user.getId());
//
//            List<InvoiceDto> invoiceDtoList1 = null;
//        for(InvoiceInDb item : invoiceDtoList) {
//            System.out.println(item.getInvoiceId());
//            Invoice invoice = null;
//            Invoice.retrieve(item.getInvoiceId());
//            InvoiceDto invoiceDto = new InvoiceDto();
//            invoiceDto.setId(invoice.getId());
//            invoiceDto.setCustomer(invoice.getCustomer());
//            invoiceDto.setObject(invoice.getObject());
//            invoiceDto.setSubtotal(invoice.getSubtotal());
//            invoiceDto.setTotal(invoice.getTotal());
//            System.out.println(invoiceDto);
//           invoiceDtoList1.add(invoiceDto);
//    }








}
