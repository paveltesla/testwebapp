package com.example.services;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;

public interface AdminService {

    boolean userIsExist(final String login, String pass);

    void delete(String login);

    void editPass(User user, String nPassRep);

    void editUser(String login, String name, int age, String birthday, float salary, ArrayList<Role> roles);

    void regUser(String login, String name, int age, String pass, ArrayList<Role> roles);
}
