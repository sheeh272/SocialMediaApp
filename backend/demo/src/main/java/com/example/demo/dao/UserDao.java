package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface UserDao {
    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAll_Users();

    Optional<User> selectUser(UUID id);

    int deleteUser(UUID id);

    int updateUser(UUID id, User user);
}
