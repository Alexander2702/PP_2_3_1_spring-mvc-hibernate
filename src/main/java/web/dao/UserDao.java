package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    User getUserById(int id);

    User updateUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();
}
