package com.troiano.springmvc.service;

import com.troiano.springmvc.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    void deleteUser(int idU);
    void saveUser(User user);
    User findById(int idU);
    void updateUser(User user);
    List<User> search(String str);

}
