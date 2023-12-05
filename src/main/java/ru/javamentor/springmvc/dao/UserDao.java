package ru.javamentor.springmvc.dao;



import ru.javamentor.springmvc.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

}
