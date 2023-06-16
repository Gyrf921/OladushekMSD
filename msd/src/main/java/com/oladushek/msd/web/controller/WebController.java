package com.oladushek.msd.web.controller;


import com.oladushek.msd.web.dto.CreateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);


    @PostMapping("/user")
    public void createUser(@RequestBody CreateUserDto createUserDto){
        //Получили запрос на создание пользоваателя и логируем его

        LOGGER.info("Create user request received: {}", createUserDto);

    }
}