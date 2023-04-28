package com.example.dao;


import com.example.domain.User;

public interface RoleDao {
    public User.Role getRoleLoginPass(final String login, final String pass);
}
