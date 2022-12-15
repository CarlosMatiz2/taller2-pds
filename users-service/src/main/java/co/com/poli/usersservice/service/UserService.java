package co.com.poli.usersservice.service;

import co.com.poli.usersservice.persistance.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void save(User user);
    Boolean delete(String id);
    User findById(String id);


}
