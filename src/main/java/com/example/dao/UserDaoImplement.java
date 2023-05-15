package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.utilites.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImplement implements UserDao {


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
                RoleDaoSingleton.getInstance().getValue().getAllRole(user.getId(), roles);
                user.setRole(roles);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> role) {
        String sql = "insert into users(login,password,name,age,birthday) values(?,?,?,?,?);";
        String sql1 = "select id from users where login = ?;";
        try (Connection connection = DBConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, age);
            preparedStatement.setDate(5, Date.valueOf(birthday));
            preparedStatement.executeUpdate();
            preparedStatement1.setString(1, login);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                RoleDaoSingleton.getInstance().getValue().addRole(id, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public User getUserByLoginPass(final String login, final String pass) {
        User result = new User();
        for (User user : UserDaoSingleton.getInstance().getValue().getAll()) {
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
                RoleDaoSingleton.getInstance().getValue().getRoleUser(login, roles);
                user.setRole(roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
