package com.oladushek.msd.service;


import com.oladushek.msd.exception.BadUserLoginException;
import com.oladushek.msd.model.UserInfo;
import com.oladushek.msd.reposiroty.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUsers() {

        return userInfoRepository.findAll();

    }


    public Optional<UserInfo> getUserById(long id){

        return userInfoRepository.findById(id);

    }

    /**
     * Создание пользователя
     * @param userInfo информация о пользователе
     */
    public void createUser(UserInfo userInfo) throws BadUserLoginException {

        try {
            checkNameSuspicious(userInfo.getLogin());
        } catch (BadUserLoginException e) {
            throw new RuntimeException(e);
        }

        if (!isUserExists(userInfo.getLogin())) {

            LOGGER.info("User created by user info: {}", userInfo);

            userInfoRepository.save(userInfo);
        } else {

            BadUserLoginException exception = new BadUserLoginException("User already exists with login " + userInfo.getLogin());

            LOGGER.error("Error creating user by user info {}", userInfo, exception);

            throw exception;

        }}
    /**
     * Возвращает информацию о пользователе по его имени
     * @param userLogin имя пользователя
     * @return информация о пользователе
     */
    public Optional<UserInfo> getUserInfoByLogin(String userLogin) {

        try {

            return userInfoRepository.findByLogin(userLogin);

        } catch (EmptyResultDataAccessException e) {

            LOGGER.error("Error getting info by name {}", userLogin, e);

            throw new RuntimeException("User not found by name " + userLogin);
        }
    }

    /**
     * Удаление пользователя
     * @param userLogin Логин пользователя
     */
    @Transactional
    public void deleteUser(String userLogin) {

        if (isUserExists(userLogin)) {

            userInfoRepository.deleteByLogin(userLogin);

            LOGGER.info("User with login {} deleted", userLogin);
        }
    }


    /**
     * Проверка на сущестование пользователя с именем
     * @param userLogin имя пользователя
     * @return true - если пользователь сущестует, иначе - false
     */
    private boolean isUserExists(String userLogin) {
        try {

            userInfoRepository.findByLogin(userLogin);

            return  true;

        } catch (EmptyResultDataAccessException e) {

            return false;
        }
    }


    /**
     * Проверка на то, что имя пользователя не содержится в стоп-листе
     * @param userLogin имя пользователя
     */
    private void checkNameSuspicious(String userLogin) throws BadUserLoginException {

        if (Set.of("administrator", "root", "system").contains(userLogin)) {

            BadUserLoginException exception = new BadUserLoginException(userLogin + " is unacceptable");

            LOGGER.error("Check name failed", exception);

            throw exception;
        }
    }
}
