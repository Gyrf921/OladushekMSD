package com.oladushek.msd.dao;

import com.oladushek.msd.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoMapper implements RowMapper<UserInfo> {

    /**
     * Возвращает информацию о пользователе
     * @param rs запись в таблице user_info
     * @param rowNum номер записи
     * @return информация о пользователе
     * @throws SQLException если в таблице нет колонки
     */
    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserInfo.builder()
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .age(rs.getInt("age"))
                .build();
    }
}
