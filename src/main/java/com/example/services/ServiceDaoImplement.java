package com.example.services;

import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.utilites.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ServiceDaoImplement implements ServiceDao {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public boolean userIsExist(String login, String pass) {
        boolean result = false;
        for (User user : userDao.getAll()) {
            if (user.getLogin().equals(login) && user.getPass().equals(pass)) {
                result = true;
                break;
            }
        }
        return result;
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
}
