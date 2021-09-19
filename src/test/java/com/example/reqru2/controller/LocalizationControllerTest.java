package com.example.reqru2.controller;


import com.example.reqru2.model.Localization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "test",password = "tom")
@AutoConfigureMockMvc
public class LocalizationControllerTest {
    
    private MockMvc mockMvc;
    private static  final String CREATE_ACCOUNT="/create_device.sql";
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    WebApplicationContext context;
    ObjectMapper objectMapper = new ObjectMapper();
//    @BeforeClass
//    public  void setUpp() throws SQLException {
//        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_ACCOUNT));
//
//    }
    @Before
    public void setUp() throws SQLException {

        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_ACCOUNT));



    }

    @org.junit.Test
    public void give_new_device_then_save_device_in_db() throws Exception {

        MvcResult result =  mockMvc.perform(get("/api/localization/1"))
             .andDo(print()) .andReturn();
                //.andExpect(status().is(200)).andExpect(jsonPath("$.id", Matchers.is(1)));
//        String resultContext = result.getResponse().getContentAsString();
//        UserDto userDto = objectMapper.readValue(resultContext, UserDto.class);
//        Assert.assertNotNull(userDto.getUsername());
        Localization localization = objectMapper.readValue(result.getResponse().getContentAsString(), Localization.class);
        assertThat(localization.getId()).isEqualTo(1L);

    }
    @org.junit.Test
    public void give_all_devices_when_devices_is_in_db() throws Exception {

        MvcResult result =  mockMvc.perform(get("/api/localization/all"))
                .andDo(print()) .andReturn();


       System.out.println(result);
        assertThat(result).isNotNull();
    }



}