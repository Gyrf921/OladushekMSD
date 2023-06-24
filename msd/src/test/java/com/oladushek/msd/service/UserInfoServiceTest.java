package com.oladushek.msd.service;

import com.oladushek.msd.exception.BadUserLoginException;
import com.oladushek.msd.model.UserInfo;
import com.oladushek.msd.reposiroty.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    private UserInfo user;

    @BeforeEach
    public void setup(){
        user = UserInfo.builder()
                .login("loginTest")
                .password("passwordTest")
                .name("nameTest")
                .surname("surnameTest")
                .age(20).build();
    }

    @Test
    void createUser() throws BadUserLoginException {
        //given - precondition or setup
        UserInfo userTest = UserInfo.builder()
                .login("loginTestCreate")
                .password("passwordTestCreate")
                .name("nameTestCreate")
                .surname("surnameTestCreate")
                .age(20).build();
        // when - action or the behaviour that we are going test
        //UserInfo savedEmployee = userInfoService.createUser(userTest);

        // then - verify the output
        //assertThat(savedEmployee).isNotNull();
        //assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}