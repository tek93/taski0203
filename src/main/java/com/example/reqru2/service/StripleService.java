package com.example.reqru2.service;

import com.example.reqru2.dto.CustomerDto;
import com.example.reqru2.dto.InvoiceDto;
import com.example.reqru2.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripleService {
    @Autowired
    UserService userService;

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
        params.put("description", user.getUsername());

        Customer customer = Customer.create(params);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        user.setStripeId(customerDto.getId());
        userService.saveUser(user);
        return customerDto;

    }

    public String putInvo(InvoiceDto invoiceDto) throws StripeException, IOException {
        String productName = invoiceDto.getProductName();
        Integer qty = invoiceDto.getQty();
        Integer ua = invoiceDto.getUa();
        User user = getUser();
        Stripe.apiKey = stripKey;

        Map<String, Object> params3 = new HashMap<>();
        params3.put("name", productName);
        params3.put("active", true);

        Product product = Product.create(params3);

        Map<String, Object> params1 = new HashMap<>();

        params1.put("customer", user.getStripeId());
        params1.put("quantity", qty);
        params1.put("unit_amount", ua);
        params1.put("currency", "pln");
        params1.put("description", productName);
        InvoiceItem invoiceItem =
                InvoiceItem.create(params1);
        InvoiceLineItemCollection invoiceLineItemCollection = new InvoiceLineItemCollection();
        invoiceLineItemCollection.setObject(invoiceItem.getObject());
        Invoice invoice = new Invoice();


        invoice.setCustomer(user.getStripeId());

        Map<String, Object> params2 = new HashMap<>();
        params2.put("customer", user.getStripeId());
        Invoice.create(params2);

        return invoice.getId();
    }

    public String getAllInvoiceFromUser() throws StripeException, JsonProcessingException {
        User user = getUser();

        Map<String, Object> params = new HashMap<>();
        params.put("customer", user.getStripeId());
        InvoiceCollection invoices = Invoice.list(params);
        String json1 = invoices.toJson();
//        System.out.println(json1);
//
//
//        Gson gson = new Gson();
//        Data data = new Data();
//
//        JsonObject body = gson.fromJson(json1, JsonObject.class);
//        JsonArray results = body.get("data").getAsJsonArray();
//
//        JsonObject firstResult = results.get(0).getAsJsonObject();
//        JsonElement amount = firstResult.get("account_name");
//        JsonElement amount_due = firstResult.get("amount_due");
//        JsonElement currency = firstResult.get("currency");
//        JsonElement customer = firstResult.get("customer");
//        JsonElement id = firstResult.get("id");
//
//        data.setAccountName(amount.getAsString());
//        data.setCustomer(customer.getAsString());
//        data.setAmountDue(amount_due.getAsString());
//        data.setCurrency(currency.getAsString());
//        data.setId(id.getAsString());
//        List<Data> dataList = new ArrayList<>();
//        dataList.add(data);

        return json1;
    }


}
