package ru.javamentor.springmvc.service;

import org.springframework.stereotype.Service;
import ru.javamentor.springmvc.model.User;


import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);
}
