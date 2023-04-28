package com.example.services;

import com.example.date.UsersArray;
import com.example.domain.User;

import java.util.Date;
import java.util.Objects;

public class ServiceDaoImplement implements ServiceDao{
    UsersArray users = new UsersArray();
    @Override
    public boolean userIsExist(String login, String pass) {
        boolean result = false;

        for(User user : users.getUsers()){
            if(user.getLogin().equals(login)){
                result = true;
                break;
            }
        }return result;
    }

    @Override
    public void delete(User user) {
        users.getUsers().remove(user);
    }

    @Override
    public void upload(User user, String[] params) {
        user.setLogin(Objects.requireNonNull(params[0], "Name cannot be a null"));
        user.setPass(Objects.requireNonNull(params[1], "Password cannot be a null"));
        users.getUsers().add(user);
    }

    @Override
    public void save(User user) {
        users.getUsers().add(user);
    }

    @Override
    public void editPass(User user, String nPassRep) {
        user.setPass(nPassRep);
    }

    @Override
    public void editUser( User user,String name, String surname, String patronymic, Date birthday, String role, String email) {
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
    }

    @Override
    public void regUser(String login, String pass, String email, User.Role role) {
        users.getUsers().add(new User(login,pass,email,role));
    }
}
