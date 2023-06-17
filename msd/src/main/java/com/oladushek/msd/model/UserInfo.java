package com.oladushek.msd.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {

    private final String login;

    private final String password;

    private final String name;

    private final String surname;

    private final Integer age;


/*    public static Builder builder() { return new Builder(); }

    *//**
     * Конструктор сделан закрытым, потому что объекты этого класса
     * надо порождать таким образом:
     * dto = User.builder().setName("John Doe").build()
     *//*
    private UserInfo(Builder builder) {
        this.name = builder.name;
    }*/



   /* public static class Builder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public UserInfo build() { return new UserInfo(this); }
    }*/
}
