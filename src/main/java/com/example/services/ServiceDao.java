package com.example.services;

import com.example.domain.User;

import java.util.Date;

public interface ServiceDao {
    boolean userIsExist(final String login, String pass);
    void delete (User user);
    void upload(User user,String[] params);
    void save(User user);
    void editPass(User user, String nPassRep);
    void editUser(User user, String name, String surname, String patronymic, Date birthday, String role, String email);
    void regUser(String login,String pass, String email, User.Role role);
}
