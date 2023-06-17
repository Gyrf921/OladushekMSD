package com.oladushek.msd.config;


import com.oladushek.msd.dao.UserInfoDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Configuration
public class DaoConfiguration {
    @Bean
    UserInfoDao userInfoDao(JdbcTemplate jdbcTemplate) {
        return new UserInfoDao(jdbcTemplate);
    }
}

