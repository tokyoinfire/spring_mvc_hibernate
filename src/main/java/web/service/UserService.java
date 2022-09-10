package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveNewUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    void editUser(User user);
}
