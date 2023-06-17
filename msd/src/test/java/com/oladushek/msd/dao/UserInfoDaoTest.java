package com.oladushek.msd.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc //simulating a http request without a server
class UserInfoDaoTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void createUser() {
    }

    @Test
    void getUserByName() throws Exception {
        this.mockMvc
                .perform(get("/users"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("calculationResult")
                        .value("4000"));
    }

    @Test
    void deleteUser() {
    }
}