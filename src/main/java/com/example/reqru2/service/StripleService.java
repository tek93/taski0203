package com.example.reqru2.service;

import com.example.reqru2.dto.CustomerDto;
import com.example.reqru2.model.InvoiceInDb;
import com.example.reqru2.model.User;
import com.example.reqru2.repository.InvoiceRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
@Service
public class StripleService {
    @Autowired
    UserService userService;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Value("${stripe.apikey}")
    String stripKey;



    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getPrincipal().toString();

        return userService.findByUsername(name);
    }

    public CustomerDto createCustomerForStripple() throws StripeException {

        User user = getUser();

        Long id = user.getId();

        Stripe.apiKey = stripKey;

        Map<String, Object> params = new HashMap<>();
        params.put(
                "description",
                "Test user"
        );

        Customer customer = Customer.create(params);
        System.out.println(customer);
        CustomerDto customerDto= new CustomerDto();
        customerDto.setId(customer.getId());
        user.setStripeId(customerDto.getId());
        userService.saveUser(user);
        return customerDto;

    }

    public Invoice putInvo(String productName, Integer qty, Integer ua) throws StripeException {
        User user= getUser();
        Stripe.apiKey = stripKey;
        System.out.println(stripKey);
        Map<String, Object> params3 = new HashMap<>();
        params3.put("name", productName);
        params3.put("active", true);

        Product product = Product.create(params3);

        Map<String, Object> params1 = new HashMap<>();
        params1.put("customer", user.getStripeId());
        params1.put( "quantity", qty);
        params1.put(   "unit_amount", ua);
        params1.put(
                "currency","pln"
        );
        InvoiceItem invoiceItem =
                InvoiceItem.create(params1);
        InvoiceLineItemCollection invoiceLineItemCollection = new InvoiceLineItemCollection();
        invoiceLineItemCollection.setObject(invoiceItem.getObject());
        Invoice invoice = new Invoice();

        System.out.println(  invoice.getLines());

        invoice.setCustomer(user.getStripeId());
        System.out.println(invoice);
        Map<String, Object> params2 = new HashMap<>();
        params2.put("customer", user.getStripeId());
        Invoice.create(params2);
        invoice.getId();
        InvoiceInDb invoiceInDb = new InvoiceInDb();
        invoiceInDb.setInvoiceId(invoice.getId());
        invoiceInDb.setIdCustomer(user.getStripeId());
        invoiceInDb.setUserId(user.getId());
        invoiceRepository.save(invoiceInDb);

        return   null;

    }

}
