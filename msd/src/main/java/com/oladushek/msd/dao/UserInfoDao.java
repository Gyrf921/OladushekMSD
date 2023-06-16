package com.oladushek.msd.dao;

import com.oladushek.msd.model.UserInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class UserInfoDao{
    /**
     * Объект для отправки SQL-запросов к БД
     */
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserInfoDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(UserInfo userInfo) {
        jdbcTemplate.update(
                "INSERT INTO user_info (name) VALUES (:name) ",
                new MapSqlParameterSource("name", userInfo.getName())
        );
    }

    /**
     * Возращает информацию о пользователе по имени
     * @param userName имя пользователя
     * @return информация о пользователе
     */
    public UserInfo getUserByName(String userName) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_info WHERE name = :name",
                new MapSqlParameterSource("name", userName),
                new UserInfoRowMapper()
        );
    }

    /**
     * Удаляет пользователя из БД
     * @param userName имя пользователя
     */
    public void deleteUser(String userName) {
        jdbcTemplate.update(
                "DELETE FROM user_info WHERE name = :name",
                new MapSqlParameterSource("name", userName)
        );
    }
}
