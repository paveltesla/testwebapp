package com.example.dao;

import com.example.domain.Role;
import com.example.utilites.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class RoleDaoImplement implements RoleDao {

    private static volatile RoleDaoImplement instance;

    private RoleDaoImplement() {
        // Приватный конструктор
    }

    public static RoleDaoImplement getInstance() {
        if (instance == null) {
            synchronized (RoleDaoImplement.class) {
                if (instance == null) {
                    instance = new RoleDaoImplement();
                }
            }
        }
        return instance;
    }

    public void getRoleUser(String login, ArrayList<Role> roles) {
        String sql = "select role_id, user_id, value from users_role left join role on role.id = users_role.role_id left join users on users.id = users_role.user_id where login = ?;";
        try (Connection connection = DBConnection.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt("role_id"),
                        resultSet.getString("value")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllRole(int userId, ArrayList<Role> roles) {
        String sql = "select user_id, role_id, role.value from users_role left join role on role.id = users_role.role_id left join users on users.id = users_role.user_id where user_id = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(resultSet.getInt("role_id"), resultSet.getString("value"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editRole(int userId, ArrayList<Role> roles) {
        String psqldell = "delete from users_role where user_id = ?;";
        String psqlinsert = "insert into users_role values (?,?)";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement dell = conn.prepareStatement(psqldell);
             PreparedStatement insert = conn.prepareStatement(psqlinsert)) {
            dell.setInt(1, userId);
            dell.executeUpdate();

            for (Role r : roles) {
                insert.setInt(1, userId);
                insert.setInt(2, r.getId());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRole(int userId, ArrayList<Role> roles) {
        String sql = "insert into users_role values (?, ?);";
        String sql1 = "select id from role where value = ?;";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps1 = conn.prepareStatement(sql1);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Role r : roles) {
                ps1.setString(1, r.getRole());
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    ps.setInt(1, userId);
                    int id = rs.getInt("id");
                    ps.setInt(2, id);
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
