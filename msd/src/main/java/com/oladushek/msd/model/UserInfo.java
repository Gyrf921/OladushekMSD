package com.oladushek.msd.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "users_info", schema="public")
public class UserInfo{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="login", length=25, nullable=false)
    private String login;

    @Column(name="password", length=50, nullable=false)
    private String password;

    @Column(name="name", length=25, nullable=false)
    private String name;

    @Column(name="surname", length=25, nullable=false)
    private String surname;

    @Column(name="age", nullable=false)
    private Integer age;

    public UserInfo() {
    }

    public UserInfo(long id, String login, String password, String name, String surname, Integer age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

}
