package com.example.dao;

import com.example.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface UserDao {

    Optional<User> get(long id);

    ArrayList<User> getAll();

    void addUser(String login, String pass, String name, String surname, String patronymic, Date birthday, User.Role role, String email);

    User getLoginPass(final String login, final String pass);

    User getLogin(final String login);

}
