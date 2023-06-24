package com.oladushek.msd.web.controller;


import com.oladushek.msd.exception.BadUserLoginException;
import com.oladushek.msd.exception.ResourceNotFoundException;
import com.oladushek.msd.model.UserInfo;
import com.oladushek.msd.service.UserInfoService;
import com.oladushek.msd.web.dto.CreateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    /*
        private final UserInfoService userInfoService;
        public WebController(@Autowired UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }*/

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/users")
    public List<UserInfo> getAllUsers() {
        LOGGER.info("Try to enter all users");

        return userInfoService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable(value = "id") Long id)
    {
        LOGGER.info("Try to enter user with {} id", id);

        UserInfo employee = null;

        try
        {
            employee = userInfoService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

            return ResponseEntity.ok().body(employee);

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @GetMapping("/users/login/{login}")
    public UserInfo getUserByLogin(@PathVariable(value = "login") String login) {
        LOGGER.info("Try to enter user with {} login", login);

        try {
            return userInfoService.getUserInfoByLogin(login)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + login));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        //Получили запрос на создание пользователя и логируем его
        LOGGER.info("Try to create user request received: {}", createUserDto);

        try {
            userInfoService.createUser(
                    UserInfo.builder()
                            .login(createUserDto.getLogin())
                            .password(createUserDto.getPassword())
                            .name(createUserDto.getName())
                            .surname(createUserDto.getSurname())
                            .age(createUserDto.getAge()).build()
            );
        } catch (BadUserLoginException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Обработчик запросов на удаление пользователя
     * @param userLogin имя пользователя
     */
    @DeleteMapping("/users/{userLogin}")
    public void deleteUser(@PathVariable String userLogin) {
        LOGGER.info("Try to delete user with login: '{}'", userLogin);

        userInfoService.deleteUser(userLogin);
    }
}
