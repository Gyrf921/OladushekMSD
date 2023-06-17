package com.oladushek.msd.web.controller;


import com.oladushek.msd.dao.UserInfoDao;
import com.oladushek.msd.model.UserInfo;
import com.oladushek.msd.service.UserInfoService;
import com.oladushek.msd.web.dto.CreateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    /**
     * Объект для операциями с БД
     */

    private final UserInfoService userInfoService;

    public WebController(@Autowired UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserDto createUserDto){
        //Получили запрос на создание пользоваателя и логируем его

        LOGGER.info("Try to create user request received: {}", createUserDto);


        userInfoService.createUser(
                UserInfo.builder()
                        .login(createUserDto.getLogin())
                        .password(createUserDto.getPassword())
                        .name(createUserDto.getName())
                        .surname(createUserDto.getSurname())
                        .age(createUserDto.getAge()).build()
        );
    }

    /**
     * Обработчик запросов на получение информации о пользователе
     * @param userName имя пользователя
     * @return информация о пользователе
     */
    @GetMapping("/users/{userName}")
    public UserInfo getUserInfo(@PathVariable String userName) {
        return userInfoService.getUserInfoByName(userName);
    }

    /**
     * Обработчик запросов на удаление пользователя
     * @param userName имя пользователя
     */
    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userInfoService.deleteUser(userName);
    }
}
