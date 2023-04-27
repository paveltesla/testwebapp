package com.example.dao;

import com.example.domain.User;

import java.util.*;

public class UsersDao implements Dao<User> {

    private static final ArrayList<User> users = new ArrayList<>();
    static {
        users.add(new User("Pavel", "123","павел","лукашенко","александрович", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
        users.add(new User("Igor", "1234", "игорь","соболев","петрович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Alexey", "1235", "алексей","носов","иванович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Anna", "1236", "анна","евтушенко","дмитриевна", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
        users.add(new User("Vitaliy", "1237", "виталий","корбин","владимирович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Alexandr", "1238", "александр","иванов","павлович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Kirill", "1239", "кирилл","шмидт","никитич", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public ArrayList<User> getAll() {
        return users;
    }

    @Override
    public void addUser(String login, String pass, String name, String surname, String patronymic, Date birthday, User.Role role, String email) {
        users.add(new User(login,pass,name,surname,patronymic,birthday,role,email));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void upload(User user, String[] params) {
        user.setLogin(Objects.requireNonNull(params[0], "Name cannot be a null"));
        user.setPass(Objects.requireNonNull(params[1], "Password cannot be a null"));
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    public boolean userIsExist(final String login, String pass){

        boolean result = false;

        for(User user : users){
            if(user.getLogin().equals(login)){
                result = true;
                break;
            }
        }return result;
    }

    @Override
    public void editPass(User user, String nPassRep) {
        user.setPass(nPassRep);
    }

    public User getLoginPass(final String login, final String pass){
        User result = new User();
        for (User user: users){
            if(user.getLogin().equals(login)&&user.getPass().equals(pass)){
                result = user;
            }
        }
        return result;
    }
    public User.Role getRoleLoginPass(final String login, final String pass){
        User.Role result = User.Role.UNKNOWN;
        for (User user: users){
            if(user.getLogin().equals(login)&&user.getPass().equals(pass)){
                result = user.getRole();
            }
        }
        return result;
    }
    public void editUser( User user,String name, String surname, String patronymic, Date birthday, String role, String email) {
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
    }
    @Override
    public User getLogin(String login) {
        User result = new User();
        for (User u:users) {
            if (u.getLogin().equals(login)){
                result = u;
            }
        }
        return result;
    }

    @Override
    public void regUser(String login, String pass, String email, User.Role role) {
        users.add(new User(login,pass,email,role));
    }
}
