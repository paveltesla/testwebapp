package com.example.dao;

import com.example.domain.*;
import com.example.utilites.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserDaoImplement implements UserDao {

    @Autowired
    RoleDao roleDao;

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
                roleDao.addRole(id, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String login) {
        String getUserIdByLogin = "select id from users where login = ?;";
        String dellRoleById = "delete from users_role where user_id = ?;";
        String deleteUserByLogin = "delete from users where login = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement1 = conn.prepareStatement(getUserIdByLogin);
             PreparedStatement preparedStatement2 = conn.prepareStatement(dellRoleById);
             PreparedStatement preparedStatement3 = conn.prepareStatement(deleteUserByLogin)) {
            preparedStatement1.setString(1, login);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                preparedStatement2.setInt(1, userId);
                preparedStatement2.executeUpdate();
                preparedStatement3.setString(1, login);
                preparedStatement3.executeUpdate();
            }
            System.out.println(login + " is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editPass(User user, String nPassRep) {
        String psql = "UPDATE users SET password = ? WHERE login = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(psql)
        ) {
            preparedStatement.setString(1, nPassRep);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(String login, String name, int age, String birthday, float salary, ArrayList<Role> roles) {
        String psql = "UPDATE users SET name = ?, age = ? ,birthday =?, salary=? WHERE login = ?;";
        String psql1 = "select id from users where login = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(psql);
             PreparedStatement preparedStatement1 = conn.prepareStatement(psql1)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setDate(3, Date.valueOf(birthday));
            preparedStatement.setFloat(4, salary);
            preparedStatement.setString(5, login);
            preparedStatement.executeUpdate();
            preparedStatement1.setString(1, login);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                roleDao.editRole(userId, roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void regUser(String login, String name, int age, String pass, ArrayList<Role> roles) {
        String psql = "insert into users(login, password, name, age) values (?,?,?,?)";
        String sqlSelectUserById = "SELECT id FROM users WHERE login = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement preparedStatement = conn.prepareStatement(psql);
             PreparedStatement secondPreparedStatement = conn.prepareStatement(sqlSelectUserById)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();

            secondPreparedStatement.setString(1, login);
            ResultSet resultSet = secondPreparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                roleDao.addRole(id, roles);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
