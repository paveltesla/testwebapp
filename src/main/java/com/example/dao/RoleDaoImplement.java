package com.example.dao;

import com.example.date.UsersArray;
import com.example.domain.User;

public class RoleDaoImplement implements RoleDao{
    UsersArray users = new UsersArray();

    @Override
    public User.Role getRoleLoginPass(final String login, final String pass){
        User.Role result = User.Role.UNKNOWN;
        for (User user: users.getUsers()){
            if(user.getLogin().equals(login)&&user.getPass().equals(pass)){
                result = user.getRole();
            }
        }
        return result;
    }
}
