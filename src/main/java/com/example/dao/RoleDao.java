package com.example.dao;


import com.example.domain.Role;

import java.util.ArrayList;
import java.util.List;

public interface RoleDao {

    void getRoleUser(String login, ArrayList<Role> roles);

    void getAllRole(int userId, ArrayList<Role> roles);

    void addRole(int userId, ArrayList<Role> roles);

    void editRole(int id, ArrayList<Role> roles);
}
