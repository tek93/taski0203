package com.example.reqru2.controller;

import com.example.reqru2.service.StripleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "test", password = "tom")
@AutoConfigureMockMvc
public class StripeInvoicesControllerTest {


    private MockMvc mockMvc;
    private static final String CREATE_ACCOUNT = "/create_device.sql";
    @Autowired
    private JdbcTemplate jdbc;
    @Value("${stripe.apikey}")
    String stripKey;

    @Autowired
    WebApplicationContext context;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    StripleService stripleService;

    //    @BeforeClass
//    public  void setUpp() throws SQLException {
//        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_ACCOUNT));
//
//    }
    @Before
    public void setUp() throws StripeException {
        given_new_invoice();
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void given_invoice_then_check_Qty() throws Exception {


        MvcResult result = mockMvc.perform(get("/api/invo/getInvoice"))
                .andDo(print()).andReturn();

    }
    //przy obecnej implementacji testowanie jednostkowe metod z serwisu wydaje mi się nie wykonalne
    //z powodu pow=bierania danych urzytkownika z contextu springa

    public void given_new_invoice() throws StripeException {
        String productName = "kaszanka";
        Integer qty = 1;
        Integer ua = 10;

        Stripe.apiKey = stripKey;

        Map<String, Object> params3 = new HashMap<>();
        params3.put("name", productName);
        params3.put("active", true);

        Product product = Product.create(params3);

        Map<String, Object> params1 = new HashMap<>();

        params1.put("customer", "cus_KDOoE0kXmiWjY0");
        params1.put("quantity", qty);
        params1.put("unit_amount", ua);
        params1.put("currency", "pln");
        params1.put("description", productName);
        InvoiceItem invoiceItem =
                InvoiceItem.create(params1);
        InvoiceLineItemCollection invoiceLineItemCollection = new InvoiceLineItemCollection();
        invoiceLineItemCollection.setObject(invoiceItem.getObject());
        Invoice invoice = new Invoice();


        invoice.setCustomer("cus_KDOoE0kXmiWjY0");

        Map<String, Object> params2 = new HashMap<>();
        params2.put("customer", "cus_KDOoE0kXmiWjY0");
        Invoice.create(params2);


    }

    @org.junit.Test
    public void give_customerId_then_have_him_invoices() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer", "cus_KDOoE0kXmiWjY0");
        InvoiceCollection invoices = Invoice.list(params);
        String json1 = invoices.toJson();
        assertThat(json1.contains("cus_KDOoE0kXmiWjY0"));
    }

}