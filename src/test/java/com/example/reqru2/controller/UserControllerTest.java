package com.example.reqru2.controller;

import com.example.reqru2.dto.UserDto;
import com.example.reqru2.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@RunWith(SpringRunner.class)

@AutoConfigureMockMvc
public class UserControllerTest {
    private MockMvc mockMvc;


    @Autowired
    WebApplicationContext context;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws SQLException {

        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();




    }

    @org.junit.Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123456");
        user.setName("tom");
        String jsonRequest = objectMapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(post("/api/user/registration").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful()).andReturn();
        String resultContext = result.getResponse().getContentAsString();
        UserDto userDto = objectMapper.readValue(resultContext, UserDto.class);
        Assert.assertNotNull(userDto.getUsername());

    }

}