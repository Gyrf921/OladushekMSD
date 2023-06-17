package com.oladushek.msd.dao;

import com.oladushek.msd.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class UserInfoDao{
    /**
     * Объект для отправки SQL-запросов к БД
     */

    private final JdbcTemplate jdbcTemplate;

    public UserInfoDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(UserInfo userInfo) {
        jdbcTemplate.update(
                "INSERT INTO Users (login, password,name,surname,age) VALUES (?,?,?,?,?) ",
                userInfo.getLogin(),
                userInfo.getPassword(),
                userInfo.getName(),
                userInfo.getSurname(),
                userInfo.getAge()
        );
    }

    /**
     * Возращает информацию о пользователе по имени
     * @param userName имя пользователя
     * @return информация о пользователе
     */
    public UserInfo getUserByName(String userName) {
        return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE name = ?",
                new Object[]{userName},
                new UserInfoMapper());
    }

    /**
     * Удаляет пользователя из БД
     * @param userName имя пользователя
     */
    public void deleteUser(String userName) {
        jdbcTemplate.update(
                "DELETE FROM Users WHERE name = ?", userName );
    }
}
