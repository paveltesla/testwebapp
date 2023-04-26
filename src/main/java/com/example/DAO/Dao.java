package com.example.DAO;

import com.example.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);
    List<T> getAll();
    void save(T t);
    void upload(T t , String[] params);
    void delete (T t);
    void addUser(String login, String pass, String name, String surname, String patronymic, Date birthday, User.Role role, String email);
    User.Role getRoleLoginPass(final String login, final String pass);

    User getLoginPass(final String login, final String pass);
    User getLogin(final String login);
    boolean userIsExist(final String login, String pass);
    void editPass(User user, String nPassRep);
    void editUser(User user, String name, String surname, String patronymic, Date birthday, String role, String email);

    void regUser(String login,String pass, String email, User.Role role);
}
