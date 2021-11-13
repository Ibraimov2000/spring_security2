package web.service;


import web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void deleteUser(long id);

    void updateUser(long id, User user);

    List<User> getUsers();

    User getUserById(long id);

    User getUserByName(String name);
}
