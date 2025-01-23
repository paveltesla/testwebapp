package com.example.dao;

import com.example.domain.*;
import com.example.utilites.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserDaoImplement implements UserDao {

//    private static volatile UserDaoImplement instance;
//
//    private UserDaoImplement() {
//    }
//
//    public static UserDaoImplement getInstance() {
//        if (instance == null) {
//            synchronized (UserDaoImplement.class) {
//                if (instance == null) {
//                    instance = new UserDaoImplement();
//                }
//            }
//        }
//        return instance;
//    }

    @Autowired UserDao userDao;
    @Autowired RoleDao roleDao;

    @Override
    public ArrayList<User> getAll() {
        User user;
        ArrayList<User> users = new ArrayList<>();
        String psql = "select * from users;";
        try (Connection connection = DBConnection.getConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(psql);
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDate("birthday"),
                        resultSet.getFloat("salary")
                );
                user.setId(resultSet.getInt("id"));
                ArrayList<Role> roles = new ArrayList<>();
                roleDao.getAllRole(user.getId(), roles);
                user.setRole(roles);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByLoginPass(final String login, final String pass) {
        User result = new User();
        for (User user : userDao.getAll()) {
            if (user.getLogin().equals(login) && user.getPass().equals(pass)) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        String psql = "select * from users where login = ?;";
        try (Connection connection = DBConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(psql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDate("birthday"),
                        resultSet.getFloat("salary")
                );
                user.setId(resultSet.getInt("id"));
                ArrayList<Role> roles = new ArrayList<>();
                roleDao.getRoleUser(login, roles);
                user.setRole(roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
