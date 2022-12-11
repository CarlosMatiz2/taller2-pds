package co.com.poli.usersservice.service;

import co.com.poli.usersservice.persistance.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void save(User user);
    void delete(User user);
    User findById(String id);


}
