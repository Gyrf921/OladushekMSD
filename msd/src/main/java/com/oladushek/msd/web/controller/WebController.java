package com.oladushek.msd.web.controller;


import com.oladushek.msd.dao.UserInfoDao;
import com.oladushek.msd.model.UserInfo;
import com.oladushek.msd.web.dto.CreateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    /**
     * Объект для операциями с БД
     * TODO: Позже надо перейти на использование сервисного слоя
     */
    private final UserInfoDao userInfoDao;

    public WebController(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserDto createUserDto){
        //Получили запрос на создание пользоваателя и логируем его

        LOGGER.info("Create user request received: {}", createUserDto);


        userInfoDao.createUser(
                UserInfo.builder().setName(createUserDto.getName()).build()
        );
    }

    /**
     * Обработчик запросов на получение информации о пользователе
     * @param userName имя пользователя
     * @return информация о пользователе
     */
    @GetMapping("/users/{userName}")
    public UserInfo getUserInfo(@PathVariable String userName) {
        return userInfoDao.getUserByName(userName);
    }

    /**
     * Обработчик запросов на удаление пользователя
     * @param userName имя пользователя
     */
    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userInfoDao.deleteUser(userName);
    }
}
