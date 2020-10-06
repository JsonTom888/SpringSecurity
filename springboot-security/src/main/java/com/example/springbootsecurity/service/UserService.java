package com.example.springbootsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/6 21:50
 */
public interface UserService {
    List<User> findAllUsers();
    @Secured("ADMIN")
    void updateUser(User user);
    @Secured({ "USER", "ADMIN" })
    void deleteUser();
}
